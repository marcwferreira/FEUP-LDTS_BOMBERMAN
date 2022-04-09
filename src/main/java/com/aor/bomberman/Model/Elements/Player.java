package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;

public class Player extends Element {
    private int bombSize;
    private int bombPlaceCoolDown;
    private int movementCoolDown;
    private int lives;
    private int coins;

    public Player(int x, int y) {
        super(x, y);
        this.bombSize = 1;
        this.bombPlaceCoolDown = 0; //time in seconds
        this.movementCoolDown = 0;
        this.lives = 0;
        this.coins = 0;
    }

    public void setPosition(Position position) {this.position = position;}
    public void setCoins(int coins) {this.coins = coins;}
    public void addBombSize() {bombSize++;}
    public void BombPlaceCoolDownSubtract() {bombPlaceCoolDown--;}
    public void MovementCoolDownBegin() {this.movementCoolDown = 8;}
    public void MovementCoolDownSubtract() {movementCoolDown--;}
    public void playerAddLife() {lives++;}
    public void playerRemoveLife() {lives--;}
    public void playerAddCoin() {
        coins++;
        if(coins==20){
            coins = 0;
            lives++;
        }
    }

    public Position getPosition(){return position;}
    public int getBombSize(){return bombSize;}
    public int getBombPlaceCoolDown(){return bombPlaceCoolDown;}
    public int getMovementCoolDown() {return movementCoolDown;}
    public int getLives(){return lives;}
    public int getCoins(){return coins;}

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    public Position placeBomb(){
        bombPlaceCoolDown = 2;
        return new Position(position.getX(),position.getY());
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5e8d63"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "P");
    }

}
