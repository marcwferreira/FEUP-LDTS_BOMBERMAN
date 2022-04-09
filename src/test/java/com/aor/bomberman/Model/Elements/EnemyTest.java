package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Elements.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnemyTest {

    Enemy littleBot = new Enemy(1, 1);
    int min = 0; int max = 2;

    @Test
    public void movementTest() {
        littleBot.move();
        int littleBotX = littleBot.getPosition().getX();
        int littleBotY = littleBot.getPosition().getY();
        Assertions.assertTrue(min <= littleBotX && littleBotX <= max);
        Assertions.assertTrue(min <= littleBotY && littleBotY <= max);
    }

}
