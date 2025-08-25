package me.alexxxychep.wlhub.items;

import be.seeseemelk.mockbukkit.MockBukkit;
import me.alexxxychep.wlhub.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WLItemTest {
    private Main plugin;

    @BeforeEach
    void setUp() {
        MockBukkit.mock();
        plugin = MockBukkit.load(Main.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testRegisterItem() {
        assertThat(WLItem.getRegisteredItems()).containsExactlyInAnyOrder("sdlfj", "sdlf");
    }

}
