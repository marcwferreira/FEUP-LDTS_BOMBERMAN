package com.aor.bomberman.Model;

import com.aor.bomberman.Control.Controller;
import com.aor.bomberman.Model.MenuStates.ControlMenuState;
import com.aor.bomberman.Model.MenuStates.EndMenuState;
import com.aor.bomberman.Model.MenuStates.MainMenuState;
import com.aor.bomberman.Model.MenuStates.PauseMenuState;
import com.aor.bomberman.View.*;
import com.aor.bomberman.View.MenuView.ControlMenuView;
import com.aor.bomberman.View.MenuView.EndMenuView;
import com.aor.bomberman.View.MenuView.MainMenuView;
import com.aor.bomberman.View.MenuView.PauseMenuView;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameModel {
    private final TerminalScreen screen;
    //Player Input control
    private Controller playerController;
    //main menu
    private final MainMenuView mainMenuView;
    private final MainMenuState mainMenu;
    //control menu
    private final ControlMenuView controlMenuView;
    private final ControlMenuState controlMenu;
    //Pause menu
    private final PauseMenuView pauseMenuView;
    private final PauseMenuState pauseMenu;
    //End menu
    private final EndMenuView endMenuView;
    private final EndMenuState endMenu;
    //World
    private final WorldView worldView;
    private WorldState worldState;

    private final int width;
    private final int height;
    private int activeState;

    ScheduledExecutorService drawAuto;
    ScheduledExecutorService timeSubAuto;
    ScheduledExecutorService gameStart;
    ScheduledExecutorService playerMoveDetector;
    ScheduledExecutorService enemiesAuto;
    ScheduledExecutorService playerMoveAuto;

    private String actionMenu;

    public MainMenuState getMainMenu() {
        return mainMenu;
    }

    public EndMenuState getEndMenu() {
        return endMenu;
    }

    public PauseMenuState getPauseMenu() {
        return pauseMenu;
    }

    public ControlMenuState getControlMenu() {
        return controlMenu;
    }

    public WorldState getWorldState() {
        return worldState;
    }

    public String getActionMenu() {
        return actionMenu;
    }

    public int getActiveState() {
        return activeState;
    }

    public void setActiveState(int activeState) {
        this.activeState = activeState;
    }

    public void setPlayerController(Controller newController) {
        this.playerController = newController;
    }

    public GameModel(int width, int height) throws IOException{
        this.width = width;
        this.height = height;

        Font font = new Font("WenQuanYi Zen Hei Mono", Font.BOLD, 20);
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.NOTHING, font);
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).setTerminalEmulatorFontConfiguration(cfg);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen((terminal));
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        //player input
        playerController = new Controller();
        //default active screen (main menu)
        this.activeState = 0;

        mainMenuView = new MainMenuView(width, height);
        controlMenuView = new ControlMenuView(width, height);
        pauseMenuView = new PauseMenuView(width, height);
        endMenuView = new EndMenuView(width, height);

        mainMenu = new MainMenuState(mainMenuView);
        controlMenu = new ControlMenuState(controlMenuView);
        pauseMenu = new PauseMenuState(pauseMenuView);
        endMenu = new EndMenuState(endMenuView);


        worldState = new WorldState(width, height);
        worldView = new WorldView(worldState);

    }

    /**
     * This method calls the appropriate draw function to display it
     * @throws IOException
     */
    private void callDraws() throws IOException {
        screen.clear();
        if(activeState==0) mainMenuView.draw(screen.newTextGraphics());
        else if(activeState==1) controlMenuView.draw(screen.newTextGraphics());
        else if(activeState==2) pauseMenuView.draw(screen.newTextGraphics());
        else if(activeState==3) endMenuView.draw(screen.newTextGraphics());
        else {worldView.draw(screen.newTextGraphics());}
        screen.refresh();
    }

    /**
     * This method process a player input
     * @param key The key a player has pressed
     * @throws IOException
     */
    public void processKey(KeyStroke key) throws IOException {
        actionMenu = "false";

        if(activeState==0){actionMenu = playerController.processKey(key,mainMenu);}
        else if(activeState==1){actionMenu = playerController.processKey(key,controlMenu);}
        else if(activeState==2){actionMenu = playerController.processKey(key,pauseMenu);}
        else if(activeState==3){actionMenu = playerController.processKey(key,endMenu);}
        else playerController.processKey(key, worldState);

        if(Objects.equals(actionMenu, "play")) {
            if (activeState==0) {
                worldState = new WorldState(width,height);
                worldView.setWorldState(worldState);
            }
            this.activeState = 4;
            runGameStart();
        }
        else if(Objects.equals(actionMenu, "mainScreen")) {activeState=0;}
        else if(Objects.equals(actionMenu, "controls")) {
            if(activeState==2){controlMenu.setGameRunningTrue();}
            else{controlMenu.setGameRunningFalse();}
            activeState=1;
        }
        else if(Objects.equals(actionMenu, "pause")) {
            activeState=2;
        }
        else if(Objects.equals(actionMenu, "end")) {activeState=3;}
        if(Objects.equals(actionMenu, "quit")) screen.close();
    }

    /**
     * checks if a key is being pressed and makes the appropriate method call if it is.
     * @throws IOException
     */
    public void playerProcess() throws IOException {
        KeyStroke key = screen.pollInput();

        //if player is in pauseView adn press esc he will go back to the game
        if (key!=null && key.getKeyType() == KeyType.Escape && activeState==4) {
            activeState=2;
            stopGameStart();
        }
        else if (key!=null && key.getKeyType() == KeyType.EOF) stopGame();
        else if (key!=null) processKey(key);
    }

    /**
     * Is called when the game is launched and starts necessary loop functions.
     * @throws IOException
     */
    public void run() throws IOException {
        drawLoop();
        playerProcessLoop();
    }

    /**
     * Is called when a gameplay starts (when the active state is the worldState) and starts necessary loops for it.
     */
    private void runGameStart(){
        timeSubLoop();
        gameTickLoop();
        enemiesLoop();
        playerMoveLoop();
    }

    /**
     * The necessary loop to keep updating what is to be shown on screen
     */
    private void drawLoop() {
        drawAuto = Executors.newSingleThreadScheduledExecutor();
        drawAuto.scheduleAtFixedRate(() -> {
            try {
                callDraws();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 34, TimeUnit.MILLISECONDS);
    }

    /**
     * Called in the run function which starts when the game itself is launched and keeps processing inputs
     */
    private void playerProcessLoop() {
        playerMoveDetector = Executors.newSingleThreadScheduledExecutor();
        playerMoveDetector.scheduleAtFixedRate(() -> {
            try {
                playerProcess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
    }

    /**
     * This method is responsible for the game timer, it starts the time and keeps decreasing it.
     */
    private void timeSubLoop(){
        timeSubAuto = Executors.newSingleThreadScheduledExecutor();
        timeSubAuto.scheduleAtFixedRate(() -> {
            if (activeState==4) {
                worldState.timeSubtract();
                worldState.playerSubtractBombCoolDown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * This method makes worldState object keep updating itself.
     */
    private void gameTickLoop(){
        gameStart = Executors.newSingleThreadScheduledExecutor();
        gameStart.scheduleAtFixedRate(() -> {
            if (activeState==4) worldState.gameTick();
            if(worldState.getGameEndCode()==1){
                endMenu.setMessageCode(0);
                activeState = 3;
                stopGameStart();
            }
            else if(worldState.getGameEndCode()==2){
                endMenu.setMessageCode(1);
                activeState = 3;
                stopGameStart();
            }
        }, 0, 5, TimeUnit.MILLISECONDS);
    }

    /**
     * This method keeps enemies moving in a reasonable speed.
     */
    private void enemiesLoop(){
        enemiesAuto = Executors.newSingleThreadScheduledExecutor();
        enemiesAuto.scheduleAtFixedRate(() -> worldState.moveEnemies(), 0, 500, TimeUnit.MILLISECONDS);
    }

    /**
     * This method run the function to reduce player wait time to move (so it can't go really fast)
     */
    private void playerMoveLoop(){
        playerMoveAuto = Executors.newSingleThreadScheduledExecutor();
        playerMoveDetector.scheduleAtFixedRate(() -> worldState.playerSubtractMoveCoolDown(),0,10,TimeUnit.MILLISECONDS);
    }

    /**
     * This method ends the schedulers working on essential methods and finishes the game.
     */
    private void stopGame(){
        drawAuto.shutdown();
        playerMoveDetector.shutdown();
        stopGameStart();
    }

    /**
     * This method ends the schedulers working on an active gameplay state and stops the gameplay state itself.
     */
    private void stopGameStart(){
        timeSubAuto.shutdown();
        gameStart.shutdown();
        enemiesAuto.shutdown();
        playerMoveAuto.shutdown();
    }

}
