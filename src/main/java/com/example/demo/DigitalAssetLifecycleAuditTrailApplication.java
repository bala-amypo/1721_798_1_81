// File: src/main/java/com/example/demo/DigitalAssetLifecycleAuditTrailApplication.java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DigitalAssetLifecycleAuditTrailApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalAssetLifecycleAuditTrailApplication.class, args);
    }
}