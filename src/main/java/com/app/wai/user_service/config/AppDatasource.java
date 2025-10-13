package com.app.wai.user_service.config;

import com.app.wai.user_service.config.properties.DatasourceProperties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AppDatasource {
    private final AppProperties appProperties;

    @Bean
    @Primary // JPA / default app datasource
    public DataSource appDataSource() {
        DatasourceProperties datasourceProperties = appProperties.getDatasource();
        var cfg = new com.zaxxer.hikari.HikariConfig();
        cfg.setDriverClassName("org.postgresql.Driver");
        cfg.setJdbcUrl(datasourceProperties.getUrl());
        cfg.setUsername(datasourceProperties.getUsername());
        cfg.setPassword(datasourceProperties.getPassword());
        return new com.zaxxer.hikari.HikariDataSource(cfg);
    }

    @Bean
    @org.springframework.boot.autoconfigure.flyway.FlywayDataSource // Flyway will use this one
    public DataSource flywayDataSource() {
        var cfg = new com.zaxxer.hikari.HikariConfig();
        cfg.setDriverClassName("org.postgresql.Driver");
        cfg.setJdbcUrl(System.getenv("SPRING_DATASOURCE_URL"));
        cfg.setUsername(System.getenv("SPRING_DATASOURCE_USERNAME"));
        cfg.setPassword(System.getenv("SPRING_DATASOURCE_PASSWORD"));
        return new com.zaxxer.hikari.HikariDataSource(cfg);
    }
}
