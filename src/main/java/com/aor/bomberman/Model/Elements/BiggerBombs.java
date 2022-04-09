package com.aor.bomberman.Model.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BiggerBombs extends Element {
    public BiggerBombs(int x, int y) {super(x, y);}

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5e8d63"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "b");
    }
}
