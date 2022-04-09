package com.aor.bomberman.View;

import com.aor.bomberman.Model.Position;
import com.aor.bomberman.View.MenuView.MainMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainMenuViewTest {

    static MainMenuView mainMenuView;
    static Position testPosition;

    @BeforeAll
    static void setup() {
        mainMenuView = new MainMenuView(0, 0);
        testPosition = new Position(0, 0);
    }

    @BeforeEach
    public void selectorPosReset() {
        mainMenuView.setSelectorPos(0, 0);
    }

    @Test
    public void updateCode0() {
        mainMenuView.update(0);
        Assertions.assertEquals(mainMenuView.getSelectorPos().getY(), testPosition.getY());
    }

    @Test
    public void updateCode1() {
        testPosition.setY(2);
        mainMenuView.update(1);
        Assertions.assertEquals(mainMenuView.getSelectorPos().getY(), testPosition.getY());
    }

    @Test
    public void updateCode2() {
        testPosition.setY(4);
        mainMenuView.update(2);
        Assertions.assertEquals(mainMenuView.getSelectorPos().getY(), testPosition.getY());
    }
}
