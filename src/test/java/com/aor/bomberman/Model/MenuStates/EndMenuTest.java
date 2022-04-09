package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.Model.MenuStates.EndMenuState;
import com.aor.bomberman.View.MenuView.EndMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class EndMenuTest {

    static EndMenuState endMenuState;
    static EndMenuView viewMock = mock(EndMenuView.class);

    public String returnFalse() { return "false"; }
    public String returnMainScreen() { return "mainScreen"; }

    @BeforeAll
    static public void setup() {
        endMenuState = new EndMenuState(viewMock);
    }

    @Test
    public void processCommand0() {
        Assertions.assertEquals(returnMainScreen(), endMenuState.processCommand(0));
    }

    @Test
    public void processCommand1() {
        Assertions.assertEquals(returnMainScreen(), endMenuState.processCommand(1));
    }

    @Test
    public void processCommand2() {
        Assertions.assertEquals(returnFalse(), endMenuState.processCommand(2));
    }

    @Test
    public void processCommand3() {
        Assertions.assertEquals(returnFalse(), endMenuState.processCommand(3));
    }

    @Test
    public void processCommand4() {
        Assertions.assertEquals(returnFalse(), endMenuState.processCommand(4));
    }

    @Test
    public void processCommand5() {
        Assertions.assertEquals(returnFalse(), endMenuState.processCommand(5));
    }

    @Test
    public void processCommand6() {
        Assertions.assertEquals(returnFalse(), endMenuState.processCommand(6));
    }
}
