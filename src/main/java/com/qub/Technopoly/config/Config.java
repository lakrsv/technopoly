package com.qub.Technopoly.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.qub.Technopoly.exception.ConfigurationException;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;

/**
 * Main Configuration Wrapper containing all configurable elements of the game.
 */
@RequiredArgsConstructor
@Value
public class Config {
    private static final String CONFIG_FILENAME = "config.json";
    private static Config config;
    private final DelayConfig delayConfig;
    private final InventoryConfig inventoryConfig;
    private final PlayerConfig playerConfig;
    private final DiceConfig diceConfig;
    private final StartConfig startConfig;
    private final FreeParkingConfig freeParkingConfig;
    private final FieldConfig[] fieldConfigs;

    /**
     * Gets the Config to use for this game.
     *
     * @return The configuration to use for the game
     */
    public static Config getConfig() {
        if (config != null) {
            return config;
        }

        getOutputSource().writeTitle("Loading Game Configuration");
        getOutputSource().writeBody("Attempting to load configuration from disk...");
        config = getConfigFromDisk();
        if (config != null) {
            getOutputSource().writeBody("Configuration loaded from disk!");
            return config;
        }

        getOutputSource().writeBody("Attempting to load default configuration...");
        config = getConfigFromResources();
        if (config != null) {
            getOutputSource().writeBody("Loaded default configuration!");
            return config;
        }

        throw new ConfigurationException("No configuration file was found! Can't start game");
    }

    private static Config getConfigFromDisk() {
        var gson = new Gson();

        Config config = null;
        var is = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
        var isr = new InputStreamReader(is);
        var br = new BufferedReader(isr);
        try (var reader = new JsonReader(br)) {
            config = gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            getOutputSource().writeBody("Failed loading configuration from disk.");
        }
        return config;
    }

    private static Config getConfigFromResources() {
        var gson = new Gson();

        Config config = null;
        try (var reader = new JsonReader(new InputStreamReader(
            Config.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME)))) {
            config = gson.fromJson(reader, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
