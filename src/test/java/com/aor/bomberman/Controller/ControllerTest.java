package com.aor.bomberman.Controller;

import com.aor.bomberman.Control.Controller;
import com.aor.bomberman.Model.MenuStates.ControlMenuState;
import com.aor.bomberman.Model.MenuStates.EndMenuState;
import com.aor.bomberman.Model.MenuStates.MainMenuState;
import com.aor.bomberman.Model.MenuStates.PauseMenuState;
import com.aor.bomberman.Model.WorldState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {

    static Controller controller;

    static KeyStroke pressEnter;
    static KeyStroke pressEsc;
    static KeyStroke pressW;
    static KeyStroke pressS;
    static KeyStroke pressA;
    static KeyStroke pressD;
    static KeyStroke pressSpace;

    static WorldState worldState;
    static ControlMenuState controlMenuState;
    static MainMenuState mainMenuState;
    static EndMenuState endMenuState;
    static PauseMenuState pauseMenuState;

    public String returnPause() { return "pause"; }
    public String returnFalse() { return "false"; }
    public String returnMainScreen() { return "mainScreen"; }
    public String returnPlay() { return "play"; }
    public String returnControls() { return "controls"; }
    public String returnQuit() { return "quit"; }

    @BeforeAll
    static void setup() {
        controller = new Controller();

        worldState = mock(WorldState.class);
        controlMenuState = mock(ControlMenuState.class);
        mainMenuState = mock(MainMenuState.class);
        endMenuState = mock(EndMenuState.class);
        pauseMenuState = mock(PauseMenuState.class);

        pressEnter = new KeyStroke(KeyType.Enter, false, false);
        pressEsc = new KeyStroke(KeyType.Escape, false, false);
        pressW = new KeyStroke('w', false, false);
        pressS = new KeyStroke('s', false, false);
        pressA = new KeyStroke('a', false, false);
        pressD = new KeyStroke('d', false, false);
        pressSpace = new KeyStroke(' ', false, false);
    }

    @Test
    public void WorldStatePressEsc() {
        when(worldState.processCommand(0)).thenReturn(returnPause());
        Assertions.assertEquals(returnPause(), controller.processKey(pressEsc, worldState));
    }

    @Test
    public void WorldStatePressEnter() {
        when(worldState.processCommand(1)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressEnter, worldState));
    }

    @Test
    public void WorldStatePressW() {
        when(worldState.processCommand(2)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, worldState));
    }

    @Test
    public void WorldStatePressS() {
        when(worldState.processCommand(3)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, worldState));
    }

    @Test
    public void WorldStatePressA() {
        when(worldState.processCommand(4)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressA, worldState));
    }

    @Test
    public void WorldStatePressD() {
        when(worldState.processCommand(5)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, worldState));
    }

    @Test
    public void WorldStatePressSpace() {
        when(worldState.processCommand(6)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, worldState));
    }

    @Test
    public void ControlMenuStatePressEscWhileGameNotRunning() {
        when(controlMenuState.processCommand(0)).thenReturn(returnMainScreen());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnMainScreen(), controller.processKey(pressEsc, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressEnterWhileGameNotRunning() {
        when(controlMenuState.processCommand(1)).thenReturn(returnMainScreen());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnMainScreen(), controller.processKey(pressEnter, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressWWhileGameNotRunning() {
        when(controlMenuState.processCommand(2)).thenReturn(returnFalse());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressSWhileGameNotRunning() {
        when(controlMenuState.processCommand(3)).thenReturn(returnFalse());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressAWhileGameNotRunning() {
        when(controlMenuState.processCommand(4)).thenReturn(returnFalse());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressA, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressDWhileGameNotRunning() {
        when(controlMenuState.processCommand(5)).thenReturn(returnFalse());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressSpaceWhileGameNotRunning() {
        when(controlMenuState.processCommand(6)).thenReturn(returnFalse());
        controlMenuState.setGameRunningFalse();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressEscWhileGameRunning() {
        when(controlMenuState.processCommand(0)).thenReturn(returnPause());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnPause(), controller.processKey(pressEsc, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressEnterWhileGameRunning() {
        when(controlMenuState.processCommand(1)).thenReturn(returnPause());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnPause(), controller.processKey(pressEnter, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressWWhileGameRunning() {
        when(controlMenuState.processCommand(2)).thenReturn(returnFalse());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressSWhileGameRunning() {
        when(controlMenuState.processCommand(3)).thenReturn(returnFalse());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressAWhileGameRunning() {
        when(controlMenuState.processCommand(4)).thenReturn(returnFalse());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressA, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressDWhileGameRunning() {
        when(controlMenuState.processCommand(5)).thenReturn(returnFalse());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, controlMenuState));
    }

    @Test
    public void ControlMenuStatePressSpaceWhileGameRunning() {
        when(controlMenuState.processCommand(6)).thenReturn(returnFalse());
        controlMenuState.setGameRunningTrue();
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, controlMenuState));
    }

    @Test
    public void EndMenuStatePressEsc() {
        when(endMenuState.processCommand(0)).thenReturn(returnMainScreen());
        Assertions.assertEquals(returnMainScreen(), controller.processKey(pressEsc, endMenuState));
    }

    @Test
    public void EndMenuStatePressEnter() {
        when(endMenuState.processCommand(1)).thenReturn(returnMainScreen());
        Assertions.assertEquals(returnMainScreen(), controller.processKey(pressEnter, endMenuState));
    }

    @Test
    public void EndMenuStatePressW() {
        when(endMenuState.processCommand(2)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, endMenuState));
    }

    @Test
    public void EndMenuStatePressS() {
        when(endMenuState.processCommand(3)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, endMenuState));
    }

    @Test
    public void EndMenuStatePressA() {
        when(endMenuState.processCommand(4)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressA, endMenuState));
    }

    @Test
    public void EndMenuStatePressD() {
        when(endMenuState.processCommand(5)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, endMenuState));
    }

    @Test
    public void EndMenuStatePressSpace() {
        when(endMenuState.processCommand(6)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, endMenuState));
    }

    @Test
    public void MainMenuStatePressEsc() {
        when(mainMenuState.processCommand(0)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressEsc, mainMenuState));
    }

    @Test
    public void MainMenuStatePressEnterCase0() {
        when(mainMenuState.processCommand(1)).thenReturn(returnPlay());
        Assertions.assertEquals(returnPlay(), controller.processKey(pressEnter, mainMenuState));
    }

    @Test
    public void MainMenuStatePressEnterCase1() {
        when(mainMenuState.processCommand(1)).thenReturn(returnControls());
        Assertions.assertEquals(returnControls(), controller.processKey(pressEnter, mainMenuState));
    }

    @Test
    public void MainMenuStatePressEnterCase2() {
        when(mainMenuState.processCommand(1)).thenReturn(returnQuit());
        Assertions.assertEquals(returnQuit(), controller.processKey(pressEnter, mainMenuState));
    }

    @Test
    public void MainMenuStatePressW() {
        when(mainMenuState.processCommand(2)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, mainMenuState));
    }

    @Test
    public void MainMenuStatePressS() {
        when(mainMenuState.processCommand(3)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, mainMenuState));
    }

    @Test
    public void MainMenuStatePressA() {
        when(mainMenuState.processCommand(4)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressA, mainMenuState));
    }

    @Test
    public void MainMenuStatePressD() {
        when(mainMenuState.processCommand(5)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, mainMenuState));
    }

    @Test
    public void MainMenuStatePressSpace() {
        when(mainMenuState.processCommand(6)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, mainMenuState));
    }

    @Test
    public void PauseMenuStatePressEsc() {
        when(pauseMenuState.processCommand(0)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressEsc, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressEnterCase0() {
        when(pauseMenuState.processCommand(1)).thenReturn(returnPlay());
        Assertions.assertEquals(returnPlay(), controller.processKey(pressEnter, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressEnterCase1() {
        when(pauseMenuState.processCommand(1)).thenReturn(returnControls());
        Assertions.assertEquals(returnControls(), controller.processKey(pressEnter, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressEnterCase2() {
        when(pauseMenuState.processCommand(1)).thenReturn(returnQuit());
        Assertions.assertEquals(returnQuit(), controller.processKey(pressEnter, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressW() {
        when(pauseMenuState.processCommand(2)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressW, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressS() {
        when(pauseMenuState.processCommand(3)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressS, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressA() {
        when(pauseMenuState.processCommand(4)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), pauseMenuState.processCommand(4));
    }

    @Test
    public void PauseMenuStatePressD() {
        when(pauseMenuState.processCommand(5)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressD, pauseMenuState));
    }

    @Test
    public void PauseMenuStatePressSpace() {
        when(pauseMenuState.processCommand(6)).thenReturn(returnFalse());
        Assertions.assertEquals(returnFalse(), controller.processKey(pressSpace, pauseMenuState));
    }
}
