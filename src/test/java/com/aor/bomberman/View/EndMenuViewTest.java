package com.aor.bomberman.View;

import com.aor.bomberman.View.MenuView.EndMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EndMenuViewTest {

    static EndMenuView endMenuView;

    public String winMsg() {
        return "YOU WON! :-D";
    }

    public String loseMsg() {
        return "YOU LOST! :(";
    }

    @BeforeAll
    static void setup() {
        endMenuView = new EndMenuView(0, 0);
    }

    @Test
    public void updateCode0() {
        endMenuView.update(0);
        Assertions.assertEquals(loseMsg(), endMenuView.getEndMsg());
    }

    @Test
    public void updateCode1() {
        endMenuView.update(1);
        Assertions.assertEquals(winMsg(), endMenuView.getEndMsg());
    }
}
