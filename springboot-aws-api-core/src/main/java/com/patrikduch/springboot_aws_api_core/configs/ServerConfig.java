package com.patrikduch.springboot_aws_api_core.configs;

/**
 * An interface for basic server setup.
 * @author Patrik Duch
 */
public interface ServerConfig {
    String getServerUrl() throws Exception;
}
