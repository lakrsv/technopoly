package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.dice.Dice}
 */
@RequiredArgsConstructor
@Value
public class DiceConfig {
    private final int minRoll;
    private final int maxRoll;
    private final int amountDice;

    // Default Configuration
    public DiceConfig(){
        minRoll = 1;
        maxRoll = 6;
        amountDice = 2;
    }
}
