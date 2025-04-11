package com.ibmecmall2025.Ibmec.Mall.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;



@Configuration
@EnableConfigurationProperties(CosmosProperties.class)
@EnableCosmosRepositories(basePackages = "com.ibmecmall2025.Ibmec.Mall.repository.cosmos")
@EnableReactiveCosmosRepositories
@PropertySource("classpath:application.properties")
public class CosmosConfiguration extends AbstractCosmosConfiguration {

    private CosmosProperties properties;

    public CosmosConfiguration(CosmosProperties properties) {
        super();
        this.properties = properties;
    }

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        // Criar um TrustManager que não valida certificados
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
        };

        // Instalar o TrustManager
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            
            return new CosmosClientBuilder()
                .endpoint(properties.getUri())
                .key(properties.getKey())
                .directMode(DirectConnectionConfig.getDefaultConfig());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Erro ao configurar SSL para Cosmos DB", e);
        }
    }

    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder().build();
    }

    @Override
    protected String getDatabaseName() {
        return this.properties.getDatabase();
    }
}