package com.aor.bomberman.Model.Elements;

import com.aor.bomberman.Model.Elements.Player;
import com.aor.bomberman.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    Player testingPlayer;
    Position testingPosition;

    @BeforeEach
    public void resetPlayerState() {
        testingPlayer = new Player(1, 1);
        testingPosition = new Position(1, 1);
    }

    @Test
    public void addCoinNormally() {
        testingPlayer.playerAddCoin();
        Assertions.assertEquals(1, testingPlayer.getCoins());
    }

    @Test
    public void add20thCoin() {
        testingPlayer.setCoins(19);
        testingPlayer.playerAddCoin();
        Assertions.assertEquals(0, testingPlayer.getCoins());
        Assertions.assertEquals(1, testingPlayer.getLives());
    }

    @Test
    public void moveUpTest() {
        testingPosition = new Position(1, 0);
        Assertions.assertEquals(testingPosition, testingPlayer.moveUp());
    }

    @Test
    public void moveDownTest() {
        testingPosition = new Position(1, 2);
        Assertions.assertEquals(testingPosition, testingPlayer.moveDown());
    }

    @Test
    public void moveLeftTest() {
        testingPosition = new Position(0, 1);
        Assertions.assertEquals(testingPosition, testingPlayer.moveLeft());
    }

    @Test
    public void moveRightTest() {
        testingPosition = new Position(2, 1);
        Assertions.assertEquals(testingPosition, testingPlayer.moveRight());
    }

    @Test
    public void bombPlacingPositionTest() {
        Assertions.assertEquals(testingPlayer.getPosition(), testingPlayer.placeBomb());
    }

    @Test
    public void bombPlacingCoolDownTest() {
        testingPlayer.placeBomb();
        Assertions.assertEquals(2, testingPlayer.getBombPlaceCoolDown());
    }
}
