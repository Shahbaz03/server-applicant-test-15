package com.mytaxi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Value("${oauth.client.id}")
	private String CLIENT_ID;
	
	@Value("${oauth.client.secret}")
	private String CLIENT_SECRET;
	
	@Value("${oauth.grant.type.password}")
	private String GRANT_TYPE_PASSWORD;
	
	@Value("${oauth.authorization.code}")
	private String AUTHORIZATION_CODE;
	
	@Value("${oauth.refresh.token}")
	private String REFRESH_TOKEN;
	
	@Value("${oauth.implicit}")
	private String IMPLICIT;
	
	@Value("${oauth.scope.read}")
	private String SCOPE_READ;
	
	@Value("${oauth.scope.write}")
	private String SCOPE_WRITE;
	
	@Value("${oauth.trust}")
	private String TRUST;
	
	@Value("${oauth.access.token.validity.seconds}")
	private Integer ACCESS_TOKEN_VALIDITY_SECONDS;
	
	@Value("${oauth.refresh.token.validity.seconds}")
    private Integer REFRESH_TOKEN_VALIDITY_SECONDS;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager);
	}
}