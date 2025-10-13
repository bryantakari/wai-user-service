package com.app.wai.user_service.config.properties;


import lombok.Getter;
import lombok.Setter;

/**
 * Datasource properties.
 */
@Getter
@Setter
public class DatasourceProperties {
    private String url;
    private String username;
    private String password;
}
