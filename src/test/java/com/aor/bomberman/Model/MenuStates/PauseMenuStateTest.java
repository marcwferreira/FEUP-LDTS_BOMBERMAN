package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.Model.MenuStates.PauseMenuState;
import com.aor.bomberman.View.MenuView.PauseMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PauseMenuStateTest {

    PauseMenuState pauseMenuState;
    PauseMenuView viewMock = mock(PauseMenuView.class);

    public String returnFalse() { return "false"; }
    public String returnPlay() { return "play"; }
    public String returnControls() { return "controls"; }
    public String returnMainScreen() { return "mainScreen"; }

    @BeforeEach
    public void resetPMState() {
        pauseMenuState = new PauseMenuState(viewMock);
    }

    @Test
    public void processCommandCase2OnBorder() {
        pauseMenuState.setSelectorID(0);

        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(2));

        Assertions.assertEquals(0, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase3OnBorder() {
        pauseMenuState.setSelectorID(2);

        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(3));

        Assertions.assertEquals(2, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase2OffBorder() {
        pauseMenuState.setSelectorID(1);

        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(2));

        Assertions.assertEquals(0, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase3OffBorder() {
        pauseMenuState.setSelectorID(1);

        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(3));

        Assertions.assertEquals(2, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID0() {
        pauseMenuState.setSelectorID(0);

        Assertions.assertEquals(returnPlay(), pauseMenuState.processCommand(1));

        Assertions.assertEquals(0, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID1() {
        pauseMenuState.setSelectorID(1);

        Assertions.assertEquals(returnControls(), pauseMenuState.processCommand(1));

        Assertions.assertEquals(1, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID2() {
        pauseMenuState.setSelectorID(2);

        Assertions.assertEquals(returnMainScreen(), pauseMenuState.processCommand(1));

        Assertions.assertEquals(2, pauseMenuState.getSelectorID());
        verify(viewMock).update(pauseMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase0() {
        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(0));
    }

    @Test
    public void processCommandCase4() {
        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(4));
    }

    @Test
    public void processCommandCase5() {
        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(5));
    }

    @Test
    public void processCommandCase6() {
        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(6));
    }
}
