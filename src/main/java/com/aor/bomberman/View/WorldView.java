package com.aor.bomberman.View;

import com.aor.bomberman.Model.Elements.*;
import com.aor.bomberman.Model.WorldState;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class WorldView {

    WorldState worldState;

    public WorldView(WorldState worldState) {
        this.worldState = worldState;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5e8d63"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(worldState.getWidth(), worldState.getHeight()), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(5,1), "TIME:");
        //add time
        graphics.putString(new TerminalPosition(11,1), String.format("%03d", worldState.getTime()));
        //
        graphics.putString(new TerminalPosition(17,1), "LIVES:");
        //add life
        graphics.putString(new TerminalPosition(24,1), String.format("%02d", worldState.getPlayer().getLives()));
        //
        graphics.putString(new TerminalPosition(29,1), "COINS:");
        //add coins counter
        graphics.putString(new TerminalPosition(36,1), String.format("%02d", worldState.getPlayer().getCoins()));

        //draws the lists of different methods in the display
        for (Barrel barrel: worldState.getBarrels()) barrel.draw(graphics);
        for (Coin coin: worldState.getCoinsMap()) coin.draw(graphics);
        for (BiggerBombs biggerBombs: worldState.getBiggerBombsMap()) biggerBombs.draw(graphics);
        for (ExtraLife extraLife: worldState.getExtraLivesMap()) extraLife.draw(graphics);
        for (Enemy enemy: worldState.getEnemies()) enemy.draw(graphics);
        for (Bomb bomb : worldState.getBombs()) bomb.draw(graphics);
        for (Wall wall : worldState.getWalls()) wall.draw(graphics);
        //draw the player
        worldState.getPlayer().draw(graphics);
    }

    public void setWorldState(WorldState worldState) {
        this.worldState = worldState;
    }
}
