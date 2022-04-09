package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Elements.BiggerBombs;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BiggerBombsTest {

    static BiggerBombs biggerBombs;
    static TextGraphics mockGraphics;

    public String returnBGColor() {
        return "#5e8d63";
    }

    public String returnFGColor() {
        return "#FFFF33";
    }

    public String returnB() {
        return "b";
    }

    @BeforeAll
    static public void setup() {
        mockGraphics = mock(TextGraphics.class);
        biggerBombs = new BiggerBombs(1, 1);
    }

    @Test
    public void testDrawCall() {
        biggerBombs.draw(mockGraphics);

        verify(mockGraphics).setBackgroundColor(TextColor.Factory.fromString(returnBGColor()));
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString(returnFGColor()));
        verify(mockGraphics).putString(new TerminalPosition(1, 1), returnB());
    }
}
