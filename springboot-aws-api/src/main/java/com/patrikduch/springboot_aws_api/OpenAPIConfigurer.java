package com.patrikduch.springboot_aws_api;

import com.patrikduch.springboot_aws_api_core.configs.ServerConfig;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Global configuration for OpenAPI generator.
 * @author Patrik Duch
 */
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfigurer {

    @Value("${server.port}")
    private int localhostPort;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * Get server name based on  application.properties
     * @return Server Object that contains server configuration of OpenAPI specification.
     */
    protected Server getServer() throws Exception {
        Server server = new Server();

        var serverName = serverConfig.getServerUrl();

        server.url(serverName);
        server.description("API specification for SpringBoot AWS API");
        return server;
    }

    /**
     * Bean for filling basic OpenAPI specification.
     */
    @Bean
    public OpenAPI tepsivoOpenAPI() throws Exception {
        return new OpenAPI()
                .addServersItem(getServer())
                .info(new Info().title("SpringBoot AWS API")
                        .description("Backend specification for SpringBoot AWS API")
                        .version("1.0.0"));
    }
}