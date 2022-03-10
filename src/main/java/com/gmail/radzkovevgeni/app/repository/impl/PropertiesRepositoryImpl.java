package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.gmail.radzkovevgeni.app.repository.RepositoryConstants.CONFIG_PROPERTIES;

public class PropertiesRepositoryImpl implements PropertiesRepository {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesRepositoryImpl.class);

    private static PropertiesRepository instance;

    private final Properties properties;

    private PropertiesRepositoryImpl() {
        this.properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    public static PropertiesRepository getInstance() {
        if (instance == null) {
            instance = new PropertiesRepositoryImpl();
        }
        return instance;
    }

    @Override
    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
