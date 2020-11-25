package com.devokado.accountManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Value("${jwt.client-id}")
    private String clientId;

    @Value("${jwt.client-secret}")
    private String clientSecret;

    @Value("${jwt.accessTokenValiditySeconds}")
    private int accessTokenValiditySeconds;

    @Value("${jwt.refreshTokenValiditySeconds}")
    private int refreshTokenValiditySeconds;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEpQIBAAKCAQEAvtteMKo5tGVC8QEcbCU+izGMaY4AZPQj/qyb1qpBXzzjzfbS\n" +
                "C1sxb5GW0hIV08GFc34djV7A99v8vepFGE9WU44lh+ZyfL88cMNOFHltjFyr8gFW\n" +
                "tiHC2YwIKXfpZNW3dM5oIREwumfAJ7nCoFl6yLfI7D1NFac7G2wQfZJFAZttMDQM\n" +
                "7M7HLXupLQ5imMyGFWPOSgfEJiDiqHHy1Y/ewVBVEBQVxKs0KQPG2GiUelbl6PQp\n" +
                "LrfAqRqraaMPzVBXPoK6et0labJ0kULATjLsvdJvDxXUL5wMjE2LnIDXkk2r9QmM\n" +
                "cNSdOvFLREk8U8DLECEpjFux1hlTQTnsQ5sckwIDAQABAoIBAF0ChtpseoahOog8\n" +
                "pF9Hnz0yPl1KaILKP6hi8echPY5uHA5JlH8YR/U5HCjvy1dQ3fo0oa49WQjxWjAr\n" +
                "NNTuBQHfKgcalPyaLZg+dZSgwHRAzUK4yEsmFNDz3x5mY4VSaX8w/6ntuLN+0DQL\n" +
                "4P9H3ABpIC+QiDpfLvzkIv0vBRYMkv7T8rHnaUQfPPcf+dBfG68srieaSVAx9Ziv\n" +
                "Vg8BXeuBJWthd9vdjdysq9avsTEXg6FrZoFxc3GuVWEHMuJ1qXqNgsidYDBGr9fJ\n" +
                "GzmPFJk9aWxlyCrUO8RHdHQE8ZuUHKE/b0uMCwTTkBA8VnEAehnMnc8nlPq/4/p2\n" +
                "LB4YYuECgYEA5mUCWSBFXFjLP4kGPh7hgOTMDZKtqtqemwC5gLF7LxNhuRelUVwb\n" +
                "VuGL5DkHSq1DmkjHmlK30WrcHL9HM7+PHDlk6qU9wQWeaiY6M3FV6bcocEjMA8E5\n" +
                "buG4Arvobga0soDvdEGusrdgcbyTS4iBuWvXhOrkOzgamgs0Zi3be8sCgYEA1BF3\n" +
                "fUdNQmd7Qs+EOzzQ+CeAEHPTWbQykzyHg1sGG0n4SNx3p47RGNETHCGScMQsJ185\n" +
                "Vdy4s5EDtv0bRWmf2rg9VZCHCNcgTbJZFQqQwDS8uu7B0XPI0zzwGrM3XP0hu9XT\n" +
                "pk7ERC9JLwqZ4X1T+wkmO84yvLPKT+ZxMyNa2VkCgYEAnqsFuPeejQChyPnHENUX\n" +
                "nDr1xIs1iyrQfBM9rcGNAliovl3ofmWednESdHRJ70Xv/rsMq903tsOPH0Uj9iGA\n" +
                "n4FvXKG8K8iA1r3I61CsSC7ZbKrVxeg2c4SDw25BnjSEEUL2pevuMipUA0BJeIVk\n" +
                "QZxhaqqRz67NszTwO0Ztl40CgYEApaS3Bn3JFRMrLjZU/Mg2GX8RAsjVzv13e5YN\n" +
                "60YP8QTIEHFUbOaa4J1+Jbz7W2V/b4lriWuMQYYFtsrgoEkueXLrRYhMUVVngAjM\n" +
                "mCjWg6stt32Cmo2r0YOJr/FITzPRQ5xzYzWnC/M7YKkRJRX1oOG2oYQgEVEFt38x\n" +
                "8FWvQpECgYEAsmaY9ryjDxci9myAApcNG6NyfXaxh1pXm5ZgU8NCUFzjKxdnnJG6\n" +
                "6/rIwxOdOJuZe/MG3Wkm+6O7dTlwXBe4/WCnS5mDiE0FGtSRNnZTdcon/J7qNs45\n" +
                "kYgw+QzFBUBV4UNgeI/Ls4+2rVZaGHdCKrWogXmNXYtPZQ+feC6hwCo=\n" +
                "-----END RSA PRIVATE KEY-----";
        converter.setSigningKey(privateKey);
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvtteMKo5tGVC8QEcbCU+\n" +
                "izGMaY4AZPQj/qyb1qpBXzzjzfbSC1sxb5GW0hIV08GFc34djV7A99v8vepFGE9W\n" +
                "U44lh+ZyfL88cMNOFHltjFyr8gFWtiHC2YwIKXfpZNW3dM5oIREwumfAJ7nCoFl6\n" +
                "yLfI7D1NFac7G2wQfZJFAZttMDQM7M7HLXupLQ5imMyGFWPOSgfEJiDiqHHy1Y/e\n" +
                "wVBVEBQVxKs0KQPG2GiUelbl6PQpLrfAqRqraaMPzVBXPoK6et0labJ0kULATjLs\n" +
                "vdJvDxXUL5wMjE2LnIDXkk2r9QmMcNSdOvFLREk8U8DLECEpjFux1hlTQTnsQ5sc\n" +
                "kwIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);

    }

}