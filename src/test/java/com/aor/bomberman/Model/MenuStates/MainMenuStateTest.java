package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.Model.MenuStates.MainMenuState;
import com.aor.bomberman.View.MenuView.MainMenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class MainMenuStateTest {

    MainMenuState mainMenuState;
    MainMenuView viewMock = mock(MainMenuView.class);

    public String returnFalse() { return "false"; }
    public String returnPlay() { return "play"; }
    public String returnControls() { return "controls"; }
    public String returnQuit() { return "quit"; }

    @BeforeEach
    public void resetMMState() {
        mainMenuState = new MainMenuState(viewMock);
    }

    @Test
    public void processCommandCase2OnBorder() {
        mainMenuState.setSelectorID(0);

        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(2));

        Assertions.assertEquals(0, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase3OnBorder() {
        mainMenuState.setSelectorID(2);

        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(3));

        Assertions.assertEquals(2, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase2OffBorder() {
        mainMenuState.setSelectorID(1);

        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(2));

        Assertions.assertEquals(0, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase3OffBorder() {
        mainMenuState.setSelectorID(1);

        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(3));

        Assertions.assertEquals(2, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID0() {
        mainMenuState.setSelectorID(0);

        Assertions.assertEquals(returnPlay(), mainMenuState.processCommand(1));

        Assertions.assertEquals(0, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID1() {
        mainMenuState.setSelectorID(1);

        Assertions.assertEquals(returnControls(), mainMenuState.processCommand(1));

        Assertions.assertEquals(1, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase1ID2() {
        mainMenuState.setSelectorID(2);

        Assertions.assertEquals(returnQuit(), mainMenuState.processCommand(1));

        Assertions.assertEquals(2, mainMenuState.getSelectorID());
        verify(viewMock).update(mainMenuState.getSelectorID());
    }

    @Test
    public void processCommandCase0() {
        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(0));
    }

    @Test
    public void processCommandCase4() {
        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(4));
    }

    @Test
    public void processCommandCase5() {
        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(5));
    }

    @Test
    public void processCommandCase6() {
        Assertions.assertEquals(returnFalse(), mainMenuState.processCommand(6));
    }
}
