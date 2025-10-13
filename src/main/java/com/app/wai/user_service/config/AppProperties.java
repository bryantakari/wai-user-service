package com.app.wai.user_service.config;

import com.app.wai.user_service.config.properties.DatasourceProperties;
import com.app.wai.user_service.config.properties.JwtProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties for application props.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private DatasourceProperties datasource;
    private JwtProperties jwt;
}
