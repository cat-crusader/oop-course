package com.example.oop;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class Properties {
    private String keycloakFile;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    private static Properties instance = null;

    public static Properties getInstance() {
        if (instance == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String pathToConfigFile = System.getenv("CONFIG_FILE");
            try {
                instance = objectMapper.readValue(new File(pathToConfigFile), Properties.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public String getKeycloakFile() {
        return keycloakFile;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }
}
