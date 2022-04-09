package com.aor.bomberman.Model;

import com.aor.bomberman.Model.Elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WorldState implements StateInterface {
    private final int width; //width of display
    private final int height; //height of display

    private final Player player;

    private int time; //total time player has to complete game
    private int gameEndCode;

    private final List<Wall> walls;
    private final List<Barrel> barrels;
    private final List<Enemy> enemies;
    private final List<Coin> coinsMap;
    private final List<BiggerBombs> biggerBombsMap;
    private final List<ExtraLife> extraLivesMap;
    private final List<Bomb> bombs;

    public WorldState(int width, int height){

        this.width = width;
        this.height = height;

        this.time = 360;
        this.gameEndCode = 0; //game not ended

        player = new Player(width/2, height/2);
        this.walls = createWalls();
        this.barrels = createBarrel();
        this.enemies = createEnemies();
        this.coinsMap = new ArrayList<>();
        this.biggerBombsMap = new ArrayList<>();
        this.extraLivesMap = new ArrayList<>();
        this.bombs = new ArrayList<>();
    }

    //Getters
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Player getPlayer() {return player;}
    public int getTime() {return time;}
    public List<Wall> getWalls() { return walls; }
    public List<Barrel> getBarrels() {return barrels;}
    public List<Bomb> getBombs() {return bombs;}
    public List<BiggerBombs> getBiggerBombsMap() {return biggerBombsMap;}
    public List<Coin> getCoinsMap() {return coinsMap;}
    public List<Enemy> getEnemies() {return enemies;}
    public List<ExtraLife> getExtraLivesMap() {return extraLivesMap;}

    /**Create walls border around the game map*/
    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 3));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 4; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    /**
     * This method creates barrels in random places on map, with spawn space in mind (doesn't generate there)
     */
    private List<Barrel> createBarrel(){
        Random random = new Random();
        ArrayList<Barrel> barrels = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            int xPos = random.nextInt(width - 2) + 1;
            int yPos = random.nextInt(height -5) + 4;
            //this ensures that barrels are not create on top of the player or block him in the beginning
            if( (xPos > width/2 -2 && xPos < width/2 +2) && (yPos > height/2 -2 && yPos < height/2 +2) ) {
                i--;
            }
            else {
                barrels.add(new Barrel(xPos,yPos));
            }
        }
        return barrels;
    }

    /**
     * This method creates enemies in random spots on map, with spawn space in mind (doesn't generate there)
     */
    private List<Enemy> createEnemies() {
        Random random = new Random();
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int xPos = random.nextInt(width - 2) + 1;
            int yPos = random.nextInt(height -5) + 4;
            //this ensures enemies are not created on top of barrels
            boolean createEnemy = barrelInPosition(new Position(xPos,yPos));
            //this ensures that enemies are not create on top of the player or block him in the beginning
            if(!createEnemy && !(xPos > width / 2 - 2 && xPos < width / 2 + 2 && yPos > height / 2 - 2 && yPos < height / 2 + 2) ) {
                enemies.add(new Enemy(xPos, yPos));
            } else i--;
        }
        return enemies;
    }

    /**
     * This method is used to check if a position is on top of a barrel
     * @param position Position we want to test
     * @return returns true if there is a barrel in the position, false if otherwise
     */
    private boolean barrelInPosition(Position position){
        for(Barrel barrel: barrels)
            if (Objects.equals(barrel.getPosition(), position)) {
                return true;
            }
        return false;
    }

    public void timeSubtract(){time--;}

    /**realize functions of the game automatically (enemies movements and detections)*/
    public void gameTick(){
        //detonate bombs and erase them
        for (int i=0;i< bombs.size();i++) {

            bombs.get(i).decreaseTime();

            //see if bombs destroyed stuff
            if(bombs.get(i).getTimeToDetonate()==0){
                Position bombPosition = bombs.get(i).getPosition();
                int bombX = bombPosition.getX();
                int bombY = bombPosition.getY();
                int bombSize = bombs.get(i).getExplosionSize();
                checkPlayerDiedBomb(bombX,bombY,bombSize);
                elementDestroyed(coinsMap,bombX,bombY,bombSize);
                elementDestroyed(biggerBombsMap,bombX,bombY,bombSize);
                elementDestroyed(extraLivesMap,bombX,bombY,bombSize);
                elementDestroyed(enemies,bombX,bombY,bombSize);
                barrelDestroyed(bombX,bombY,bombSize);
            }

            if(bombs.get(i).getTimeToDetonate()<-10){
                bombs.remove(i);
                i--;
            }
        }

        //see if player died by enemy
        checkPlayerDiedEnemy();

        //see if player won (no enemies left)
        checkPlayerWon();

        //game ends if time ends
        if(time<=0) {gameEndCode = 1;}

    }

    /**checks if other objects was destroyed*/
    private <T extends Element> void elementDestroyed(List<T> destroy, int bombX, int bombY, int bombSize){
        for(int i=0;i<destroy.size();i++){
            Position elemPosition = destroy.get(i).getPosition();
            int elemX = elemPosition.getX();
            int elemY = elemPosition.getY();

            if(elemX==bombX && (elemY <= bombY+bombSize && elemY >= bombY-bombSize)) {
                destroy.remove(i);
                i--;
                continue;
            }
            if(elemY==bombY && (elemX <= bombX+bombSize && elemX>=bombX-bombSize)){
                destroy.remove(i);
                i--;
            }

        }
    }

    /**checks if a barrel was destroyed*/
    private void barrelDestroyed(int bombX, int bombY, int bombSize){
        for(int j=0;j<barrels.size();j++){
            Position barrelPosition = barrels.get(j).getPosition();
            int barrelX = barrelPosition.getX();
            int barrelY = barrelPosition.getY();

            if(barrelX==bombX && (barrelY <= bombY+bombSize && barrelY >= bombY-bombSize)) {
                barrelDrop(barrelPosition);
                barrels.remove(j);
                j--;
                continue;
            }
            if(barrelY==bombY && (barrelX <= bombX+bombSize && barrelX>=bombX-bombSize)){
                barrelDrop(barrelPosition);
                barrels.remove(j);
                j--;
            }
        }
    }

    /**Code to perform loot box drop when a barrel is broken*/
    public void barrelDrop(Position position){
        if(new Random().nextInt(100) <=8){
            coinsMap.add(new Coin(position.getX(), position.getY()));
        }
        else if(new Random().nextInt(100) <=3){
            biggerBombsMap.add(new BiggerBombs(position.getX(), position.getY()));
        }
        else if(new Random().nextInt(100) <=2){
            extraLivesMap.add(new ExtraLife(position.getX(), position.getY()));
        }
    }

    /**calls function to subtract from player bomb placement cool down*/
    public void playerSubtractBombCoolDown(){
        if(player.getBombPlaceCoolDown()>0){
            player.BombPlaceCoolDownSubtract();
        }
    }

    /**calls function to subtract from player bomb placement cool down*/
    public void playerSubtractMoveCoolDown(){
        if(player.getMovementCoolDown()>0){
            player.MovementCoolDownSubtract();
        }
    }


    /**
     * This method checks if player can move (it is also used to see if enemies can move)
     */
    private boolean playerCanMove(Position position){
        if (position.getX() < 0 || position.getX() > width - 1) return false;
        if (position.getY() < 0 || position.getY() > height - 1) return false;
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        for (Barrel barrel : barrels) {
            if (barrel.getPosition().equals(position)) return false;
        }
        for (Bomb bomb : bombs) {
            if (bomb.getPosition().equals(position)) return false;
        }
        return true;
    }

    /**moves the player*/
    public void movePlayer(Position position){
        if (playerCanMove(position)){
            if(player.getMovementCoolDown()<=0) {
                player.MovementCoolDownBegin();
                player.setPosition(position);
            }
        }
    }

    /**moves all enemies at random on the map (only if it can move)*/
    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            Position enemyPosition = enemy.move();
            if (playerCanMove(enemyPosition)) enemy.setPosition(enemyPosition);
        }
    }

    /**Checks if player has won the game (eliminated all the enemies)*/
    private void checkPlayerWon(){
        if(enemies.size()==0){
            System.out.println("You won!");
            gameEndCode = 2;
        }
    }

    /**Checks if player has died*/
    private void playerDied() {
        if (player.getLives() > 0) {
            player.setPosition(new Position(width / 2, height / 2));
            player.playerRemoveLife();
        } else if (player.getLives() == 0) {
            System.out.println("You died!");
            gameEndCode = 1;
        }
    }

    /**checks if player has bombed itself to oblivion*/
    private void checkPlayerDiedBomb(int bombX, int bombY, int bombSize){
        Position playerPos = player.getPosition();
        int elemX = playerPos.getX();
        int elemY = playerPos.getY();

        if(elemX==bombX && (elemY <= bombY+bombSize && elemY >= bombY-bombSize)) {
            playerDied();
        }
        if(elemY==bombY && (elemX <= bombX+bombSize && elemX>=bombX-bombSize)){
            playerDied();
        }
    }

    /**returns when game has ended*/
    public int getGameEndCode(){return gameEndCode;}

    /**Adds a coin to player*/
    private void playerAddCoin(){
        for (Coin coin: coinsMap) {
            if(player.getPosition().equals(coin.getPosition())){
                coinsMap.remove(coin);
                player.playerAddCoin();
                break;
            }
        }
    }

    /**Makes player bomb a bit bigger*/
    private void playerBiggerBombs(){
        for (BiggerBombs biggerBombs: biggerBombsMap) {
            if(player.getPosition().equals(biggerBombs.getPosition())){
                biggerBombsMap.remove(biggerBombs);
                player.addBombSize();
                break;
            }
        }
    }

    /**Adds extra life to player*/
    private void playerExtraLife(){
        for (ExtraLife extraLife: extraLivesMap) {
            if(player.getPosition().equals(extraLife.getPosition())){
                extraLivesMap.remove(extraLife);
                player.playerAddLife();
                break;
            }
        }
    }

    /**adds bomb to bomb list*/
    private void addBomb(Position position, int size){
        this.bombs.add(new Bomb(position.getX(), position.getY(), size));
    }

    /**checks if an enemy has gotten to a player and killed it*/
    private void checkPlayerDiedEnemy(){
        for (Enemy enemy: enemies)
            if (player.getPosition().equals(enemy.getPosition())) {
                playerDied();
            }
    }

    /**process key that was detected*/
    public String processCommand(int commandID) {
        switch (commandID) {
            case 0 -> { return "pause"; }
            case 2 -> movePlayer(player.moveUp());
            case 3 -> movePlayer(player.moveDown());
            case 4 -> movePlayer(player.moveLeft());
            case 5 -> movePlayer(player.moveRight());
            case 6 -> { if (player.getBombPlaceCoolDown()==0) addBomb(player.placeBomb(),player.getBombSize()); }
        }

        playerAddCoin();
        playerBiggerBombs();
        playerExtraLife();

        return "false";
    }

}
