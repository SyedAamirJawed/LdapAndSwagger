package com.ldap.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/ldap","dynamic",
                    "/v2/api-docs", "/v2/api-docs/**", "/v3/api-docs", "/v3/api-docs/**", 
                    "/v3/api-docs.yaml", "/swagger-resources", "/swagger-resources/**", 
                    "/configuration/ui", "/configuration/security", "/swagger-ui/**", 
                    "/swagger-ui.html", "/swagger-ui/index.html", "/webjars/**", 
                    "/swagger-ui.html", "/api-docs/**", "/authenticate"
                ).permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin().defaultSuccessUrl("/", true)
            .and()
            .httpBasic(); // Adding basic auth for simplicity

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(BaseLdapPathContextSource source) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
        factory.setUserDnPatterns("cn={0},ou=users,ou=system");
        return factory.createAuthenticationManager();
    }

    //--------- LDAP Configuration ----------    
    @Bean
    @Primary
    public BaseLdapPathContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:10389"); 
        contextSource.setUserDn("uid=admin,ou=system"); 
        contextSource.setPassword("secret"); 
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(@Qualifier("contextSource") BaseLdapPathContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
    
    
    
//	 @Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth
//	      .ldapAuthentication()
//	        .userDnPatterns("uid={0},ou=people")
//	        .groupSearchBase("ou=groups")
//	        .contextSource()
//	          .url("ldap://localhost:8389/dc=springframework,dc=org")
//	          .and()
//	        .passwordCompare()
//	          //.passwordEncoder(new BCryptPasswordEncoder())
//	          .passwordAttribute("userPassword");
//	  }
}
