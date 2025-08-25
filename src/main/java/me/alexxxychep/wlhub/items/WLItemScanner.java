package me.alexxxychep.wlhub.items;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WLItemScanner {

    public static List<Class<? extends WLItem>> findWLItemSubclasses() {
        List<Class<? extends WLItem>> subclasses = new ArrayList<>();
        String packageName = "me.alexxxychep.wlhub.items";

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            URL resource = classLoader.getResource(path);

            if (resource != null) {
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    scanDirectory(directory, packageName, subclasses);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subclasses;
    }

    private static void scanDirectory(File directory, String packageName,
                                      List<Class<? extends WLItem>> subclasses) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), subclasses);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' +
                    file.getName().substring(0, file.getName().length() - 6);
                System.out.println(className);
                try {
                    Class<?> clazz = Class.forName(className);
                    if (WLItem.class.isAssignableFrom(clazz) &&
                        !clazz.equals(WLItem.class) &&
                        !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) {

                        subclasses.add(clazz.asSubclass(WLItem.class));
                    }
                } catch (ClassNotFoundException | ClassCastException e) {
                    // Ignore classes that can't be loaded or aren't WLItem subclasses
                }
            }
        }
    }

    public static void processWLItems() {
        List<Class<? extends WLItem>> subclasses = findWLItemSubclasses();

        for (Class<? extends WLItem> clazz : subclasses) {
            try {
                WLItem instance = clazz.getDeclaredConstructor().newInstance();
                System.out.println("Processing: " + clazz.getSimpleName());
                // Do something with the instance

            } catch (Exception e) {
                System.err.println("Error creating instance of " + clazz.getName() + ": " + e.getMessage());
            }
        }
    }
}
