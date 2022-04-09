package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Elements.Bomb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BombTest {

    Bomb testBomb = new Bomb(0, 0, 1);

    @BeforeEach
    public void resetBombTickTo0() {
        testBomb.setTimeToDetonate(0);
    }

    @Test
    public void timeTickingTest() {
        testBomb.decreaseTime();
        Assertions.assertEquals(-1, testBomb.getTimeToDetonate());
    }

    @Test
    public void detonationTest() {
        testBomb.checkDetonated();
        Assertions.assertEquals(true, testBomb.isDetonated());
    }
}
