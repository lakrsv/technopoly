package com.qub.Technopoly;

import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.exception.GameStateException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Bootstrap responsible for the game loop.
 *
 * @author lakrs
 */
@RequiredArgsConstructor
public class Game {
    // TODO - Initialize Game..
    // TODO - Allow loading game from disk...

    @NonNull
    @Getter
    private final Board board;
    private boolean gameRunning = false;

    /**
     * Ends the game
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is not running
     *
     * @throws GameStateException When the game is not running
     */
    public void stop() throws GameStateException {
        if (!gameRunning) {
            throw new GameStateException("Can't stop game as game is not running");
        }
        gameRunning = false;
    }

    /**
     * Check whether the game is running
     *
     * @return Whether the game is running or not
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Starts a new game
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is already running
     *
     * @throws GameStateException When the game is already running
     */
    public void start() throws GameStateException {
        if (gameRunning) {
            throw new GameStateException("Can't start game as game is already running");
        }
        gameRunning = true;
    }

    /**
     * The game loop. Is responsible for updating all actors in the game, and calling methods on them.
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is not running
     *
     * @throws GameStateException When the game is not running
     */
    public void update() throws GameStateException {
        if (!gameRunning) {
            throw new GameStateException("Can't update game as game is not running");
        }

        // TODO - update game.
        final var nextActor = board.getNextActor();
        nextActor.setActive(this);

        while (nextActor.isActive()) {
            if (nextActor.update(this)) {
                nextActor.setInactive();
            }
        }
    }
}
