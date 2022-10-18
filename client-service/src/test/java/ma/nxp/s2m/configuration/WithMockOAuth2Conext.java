package ma.nxp.s2m.configuration;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockOAuth2SecurityContextFactory.class)
public @interface WithMockOAuth2Conext {

    String authorities() default "";
}