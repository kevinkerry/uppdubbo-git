package com.csii.upp.util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class EnvironmentPropertyLoader {
    private static final String DEFAULT_ENVIRONMENT = "dev";
    private static final String ENVIRONMENT_KEY = "core.environment";
    private static final String CONFIG_FILE_NAME = "config/system.properties";

    public void loadAndSetEnvironmentProperty() {
        System.setProperty(ENVIRONMENT_KEY, loadEnvironmentProperty());
    }

    private String loadEnvironmentProperty() {
        return loadEnvironmentProperty(CONFIG_FILE_NAME);
    }

    private String loadEnvironmentProperty(String configFileName) {
        try {
            Configuration config = new Configurations().properties(configFileName);
            if (config.containsKey(ENVIRONMENT_KEY)) {
                return config.getProperty(ENVIRONMENT_KEY).toString();
            } else {
                // 没有配置的话则默认为开发环境
                return DEFAULT_ENVIRONMENT;
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Error reading properties file: " + configFileName, e);
        }
    }
}
