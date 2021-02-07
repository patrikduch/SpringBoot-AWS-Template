package com.patrikduch.springboot_aws_api_core.configs;

import java.util.Map;

/**
 * An interface for accessing YAML file outside JAR archive.
 * @author Patrik Duch
 */
public interface YamlConfig {
    Map<String, String> getServer();
}
