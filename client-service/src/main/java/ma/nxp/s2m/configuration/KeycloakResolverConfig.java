package ma.nxp.s2m.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;

@Configuration
public class KeycloakResolverConfig {

    /**
     * Required to handle spring boot configurations
     *
     * @return KeycloakSpringBootConfigResolver
     */
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}