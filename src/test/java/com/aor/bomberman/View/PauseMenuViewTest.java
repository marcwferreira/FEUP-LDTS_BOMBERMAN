package com.aor.bomberman.View;

import com.aor.bomberman.Model.Position;
import com.aor.bomberman.View.MenuView.PauseMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PauseMenuViewTest {

    static PauseMenuView pauseMenuView;
    static Position testPosition;

    @BeforeAll
    static void setup() {
        pauseMenuView = new PauseMenuView(0, 0);
        testPosition = new Position(0, 0);
    }

    @BeforeEach
    public void selectorPosReset() {
        pauseMenuView.setSelectorPos(0, 0);
    }

    @Test
    public void updateCode0() {
        pauseMenuView.update(0);
        Assertions.assertEquals(pauseMenuView.getSelectorPos().getY(), testPosition.getY());
    }

    @Test
    public void updateCode1() {
        testPosition.setY(2);
        pauseMenuView.update(1);
        Assertions.assertEquals(pauseMenuView.getSelectorPos().getY(), testPosition.getY());
    }

    @Test
    public void updateCode2() {
        testPosition.setY(4);
        pauseMenuView.update(2);
        Assertions.assertEquals(pauseMenuView.getSelectorPos().getY(), testPosition.getY());
    }
}
