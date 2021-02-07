package com.patrikduch.springboot_aws_api_localhost.config;

import com.patrikduch.springboot_aws_api_core.configs.YamlConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  Access to the external YAML config file for development setup.
 * @author Patrik Duch
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="development")
public class YamlDevelopmentConfig implements YamlConfig {
    private Map<String, String> server = new HashMap<>();

    @Override
    public Map<String, String> getServer() {
        return server;
    }
}
