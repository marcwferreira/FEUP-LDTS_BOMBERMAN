package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Barrel extends Element {
    public Barrel(int x, int y) {super(x,y);}

    public void setPosition(Position position) {this.position = position;}

    public Position getPosition() {return position;}

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ce7e00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }
}
