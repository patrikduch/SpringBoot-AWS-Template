package com.patrikduch.springboot_aws_api.configs;

import com.patrikduch.springboot_aws_api.YamlProductionConfig;
import com.patrikduch.springboot_aws_api_core.configs.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Configuration of server for production environment.
 * @author Patrik Duch
 */
@Profile("production")
@Service
public class ServerProductionConfig implements ServerConfig {

    @Autowired
    private YamlProductionConfig config;

    @Override
    public String getServerUrl() throws Exception {
        return config.getServer().get("url");
    }
}
