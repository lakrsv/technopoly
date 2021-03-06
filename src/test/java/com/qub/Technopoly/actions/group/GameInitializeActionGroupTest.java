package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInitializeActionGroupTest {
    private Board testBoard;
    private Game testGame;

    private ActionGroup actionGroup;

    @BeforeEach
    public void setup() {
        testBoard = new Board();
        testGame = new Game(testBoard);

        actionGroup = new GameInitializeActionGroup(testGame, testBoard);
    }

    @Test
    public void describeDoesNotThrow() {
        actionGroup.describe();
    }

    @Test
    public void describeActionsDoesNotThrow() {
        actionGroup.describeActions();
    }

    @Test
    public void getActionsIsNotNull() {
        assertNotNull(actionGroup.getActions());
    }

    @Test
    public void getActionsIsNotEmpty() {
        assertTrue(actionGroup.getActions().length > 0);
    }
}
