package ma.nxp.s2m.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class OAuthFeignConfig implements RequestInterceptor {

    private static final Pattern BEARER_TOKEN_HEADER_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
            Pattern.CASE_INSENSITIVE);

    @Value("${s2m.feign.securedApplications}")
    List<String> feignSecuredApplications;

    @Override
    public void apply(RequestTemplate template) {
        //if(feignSecuredApplications.contains(template.feignTarget().name())){
        if(feignSecuredApplications.stream().anyMatch(s->s.equalsIgnoreCase(template.feignTarget().name()))){
            final String authorization = HttpHeaders.AUTHORIZATION;
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(requestAttributes)) {
                String authorizationHeader = requestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
                Matcher matcher = BEARER_TOKEN_HEADER_PATTERN.matcher(authorizationHeader);
                if (matcher.matches()) {
                    template.header(authorization);
                    template.header(authorization, authorizationHeader);
                }
            }
        }

    }
}
