package com.patrikduch.springboot_aws_api;

import com.patrikduch.springboot_aws_api_core.configs.YamlConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Access to the external YAML config file for production setup.
 * @author Patrik Duch
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="production")
public class YamlProductionConfig implements YamlConfig {
    private Map<String, String> server = new HashMap<>();

    public Map<String, String> getServer() {
        return server;
    }
}