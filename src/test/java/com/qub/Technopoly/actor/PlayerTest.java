package com.qub.Technopoly.actor;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private static final String EXPECTED_NAME = "Lars";
    private Actor TEST_PLAYER = new Player(EXPECTED_NAME, new Inventory());

    @Test
    public void setActiveIsActive() {
        var game = new Game(new Board());
        TEST_PLAYER.setActive(game);
        assertTrue(TEST_PLAYER.isActive());
    }

    @Test
    public void setInactiveIsInactive() {
        var game = new Game(new Board());
        TEST_PLAYER.setActive(game);
        TEST_PLAYER.setInactive();
        assertFalse(TEST_PLAYER.isActive());
    }

    @Test
    public void playerIsInactiveByDefault() {
        assertFalse(TEST_PLAYER.isActive());
    }

    @Test
    public void getNameReturnsExpected() {
        assertEquals(EXPECTED_NAME, TEST_PLAYER.getActorName());
    }
}
