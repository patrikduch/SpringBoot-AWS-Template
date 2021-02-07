package com.patrikduch.springboot_aws_api_localhost.config;


import com.patrikduch.springboot_aws_api_core.interfaces.configs.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *  Setup of server for development environment.
 * @author Patrik Duch
 */
@Service
@Profile("development")
public class ServerConfigDevelopment implements ServerConfig {

    @Autowired
    private YamlDevelopmentConfig _config;

}
