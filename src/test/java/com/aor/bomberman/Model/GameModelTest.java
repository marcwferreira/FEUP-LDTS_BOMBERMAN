package com.aor.bomberman.Model;

import com.aor.bomberman.Control.Controller;
import com.aor.bomberman.Model.GameModel;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;


public class GameModelTest {


    GameModel gameModelTest = new GameModel(60, 30);
    Controller mockController = mock(Controller.class);

    static KeyStroke pressEnter;
    static KeyStroke pressEsc;
    static KeyStroke pressW;
    static KeyStroke pressS;
    static KeyStroke pressA;
    static KeyStroke pressD;
    static KeyStroke pressSpace;

    public GameModelTest() throws IOException {
    }

    public String returnPause() { return "pause"; }
    public String returnFalse() { return "false"; }
    public String returnMainScreen() { return "mainScreen"; }
    public String returnPlay() { return "play"; }
    public String returnControls() { return "controls"; }
    public String returnQuit() { return "quit"; }

    @BeforeAll
    static public void setup() {
        pressEnter = new KeyStroke(KeyType.Enter, false, false);
        pressEsc = new KeyStroke(KeyType.Escape, false, false);
        pressW = new KeyStroke('w', false, false);
        pressS = new KeyStroke('s', false, false);
        pressA = new KeyStroke('a', false, false);
        pressD = new KeyStroke('d', false, false);
        pressSpace = new KeyStroke(' ', false, false);
    }

    @BeforeEach
    public void resetGameModel() {
        try {
            GameModel gameModelTest = new GameModel(60, 30);
            gameModelTest.setPlayerController(mockController);
        } catch (IOException e){
            e.printStackTrace();
        }
            mockController = mock(Controller.class);
            gameModelTest.setPlayerController(mockController);
    }

    @Test
    public void controllerProcessKeyCheckOnMM() throws IOException {
        KeyStroke generic = pressS;
        when(mockController.processKey(generic, gameModelTest.getMainMenu())).thenReturn(returnFalse());

        gameModelTest.setActiveState(0);
        gameModelTest.processKey(generic);

        verify(mockController).processKey(generic, gameModelTest.getMainMenu());
    }

    @Test
    public void controllerProcessKeyCheckOnCM() {

        KeyStroke generic = pressS;
        when(mockController.processKey(generic, gameModelTest.getMainMenu())).thenReturn(returnFalse());

        gameModelTest.setActiveState(1);
        try {
            gameModelTest.processKey(generic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(mockController).processKey(generic, gameModelTest.getControlMenu());
    }

    @Test
    public void controllerProcessKeyCheckOnPM() {

        KeyStroke generic = pressS;
        when(mockController.processKey(generic, gameModelTest.getMainMenu())).thenReturn(returnFalse());

        gameModelTest.setActiveState(2);
        try {
            gameModelTest.processKey(generic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(mockController).processKey(generic, gameModelTest.getPauseMenu());
    }

    @Test
    public void controllerProcessKeyCheckOnEM() {

        KeyStroke generic = pressS;
        when(mockController.processKey(generic, gameModelTest.getMainMenu())).thenReturn(returnFalse());

        gameModelTest.setActiveState(3);
        try {
            gameModelTest.processKey(generic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(mockController).processKey(generic, gameModelTest.getEndMenu());
    }

    @Test
    public void controllerProcessKeyCheckOnWS() {

        KeyStroke generic = pressS;
        when(mockController.processKey(generic, gameModelTest.getMainMenu())).thenReturn(returnFalse());

        gameModelTest.setActiveState(4);
        try {
            gameModelTest.processKey(generic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(mockController).processKey(generic, gameModelTest.getWorldState());
    }

}
