package com.example.smartcampus;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages(
                "com.example.smartcampus.resources",
                "com.example.smartcampus.mapper",
                "com.example.smartcampus.filter"
        );
    }
}