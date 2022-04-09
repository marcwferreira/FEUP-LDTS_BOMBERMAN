package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bomb extends Element {
    private final int explosionSize;
    private int timeToDetonate;
    private boolean detonated;

    public Bomb(int x, int y, int size) {
        super(x,y);
        this.explosionSize = size;
        this.timeToDetonate = 500;
        this.detonated = false;
    }

    public void setPosition(Position position) {this.position = position;}
    public void decreaseTime() {this.timeToDetonate--;}

    public Position getPosition() {return position;}
    public int getExplosionSize() {return explosionSize;}
    public int getTimeToDetonate() {return timeToDetonate;}

    public boolean isDetonated() {
        return detonated;
    }

    public void setTimeToDetonate(int timeToDetonate) {
        this.timeToDetonate = timeToDetonate;
    }

    public void checkDetonated(){
        if(timeToDetonate<=0) detonated = true;
    }

    public void draw(TextGraphics graphics) {

        checkDetonated();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#5e8d63"));
        graphics.enableModifiers(SGR.BOLD);
        if(!detonated) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "B");
        }
        else{
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
            for(int i=1;i<=explosionSize;i++){
                graphics.putString(new TerminalPosition(position.getX()+i, position.getY()), "X");
                graphics.putString(new TerminalPosition(position.getX()-i, position.getY()), "X");
                graphics.putString(new TerminalPosition(position.getX(), position.getY()+i), "X");
                if(position.getY()-i>3) {
                    graphics.putString(new TerminalPosition(position.getX(), position.getY() - i), "X");
                }
            }
        }
    }

}
