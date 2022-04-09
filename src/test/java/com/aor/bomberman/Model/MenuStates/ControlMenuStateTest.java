package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.Model.MenuStates.ControlMenuState;
import com.aor.bomberman.View.MenuView.ControlMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ControlMenuStateTest {

    ControlMenuState controlMenuState;
    ControlMenuView viewMock = mock(ControlMenuView.class);

    public String returnPause() { return "pause"; }
    public String returnFalse() { return "false"; }
    public String returnMainScreen() { return "mainScreen"; }

    @BeforeEach
    public void resetCMState() {
        controlMenuState = new ControlMenuState(viewMock);
    }

    @Test
    public void processCommand0GameRunning() {
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnPause(), controlMenuState.processCommand(0));
    }

    @Test
    public void processCommand0GameNotRunning() {
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnMainScreen(), controlMenuState.processCommand(0));
    }

    @Test
    public void processCommand1GameRunning() {
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnPause(), controlMenuState.processCommand(1));
    }

    @Test
    public void processCommand1GameNotRunning() {
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnMainScreen(), controlMenuState.processCommand(1));
    }

    @Test
    public void processCommand2() {
        Assertions.assertEquals(returnFalse(), controlMenuState.processCommand(2));
    }

    @Test
    public void processCommand3() {
        Assertions.assertEquals(returnFalse(), controlMenuState.processCommand(3));
    }

    @Test
    public void processCommand4() {
        Assertions.assertEquals(returnFalse(), controlMenuState.processCommand(4));
    }
    @Test
    public void processCommand5() {
        Assertions.assertEquals(returnFalse(), controlMenuState.processCommand(5));
    }

    @Test
    public void processCommand6() {
        Assertions.assertEquals(returnFalse(), controlMenuState.processCommand(6));
    }
}
