package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Elements.Coin;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoinTest {

    static Coin coin;
    static TextGraphics mockGraphics;

    public String returnBGColor() {
        return "#5e8d63";
    }

    public String returnFGColor() {
        return "#FFFF33";
    }

    public String returnStar() {
        return "*";
    }

    @BeforeAll
    static public void setup() {
        mockGraphics = mock(TextGraphics.class);
        coin = new Coin(1, 1);
    }

    @Test
    public void testDrawCall() {
        coin.draw(mockGraphics);

        verify(mockGraphics).setBackgroundColor(TextColor.Factory.fromString(returnBGColor()));
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString(returnFGColor()));
        verify(mockGraphics).putString(new TerminalPosition(1, 1), returnStar());
    }
}
