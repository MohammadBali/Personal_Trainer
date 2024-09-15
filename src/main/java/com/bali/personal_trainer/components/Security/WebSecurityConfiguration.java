package com.bali.personal_trainer.components.Security;
import com.bali.personal_trainer.components.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    private final CustomAccessDeniedHandler  customAccessDeniedHandler;

    @Autowired
    public WebSecurityConfiguration(CustomAccessDeniedHandler customAccessDeniedHandler)
    {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    List<String> allowedRequests = Constants.noAuthenticationRequests;
    List<String> adminRequests = Constants.adminAuthenticationRequests;

    List<AntPathRequestMatcher> noAuthenticationMatcher = allowedRequests.stream()
            .map(AntPathRequestMatcher::new)
            .collect(Collectors.toList());

    List<AntPathRequestMatcher> adminAuthenticationMatcher = adminRequests.stream()
            .map(AntPathRequestMatcher::new)
            .collect(Collectors.toList());

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        return auth.getOrBuild();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(noAuthenticationMatcher.toArray(new AntPathRequestMatcher[0])).permitAll()

                        .requestMatchers(adminAuthenticationMatcher.toArray(new AntPathRequestMatcher[0])).hasRole("ADMIN") //Admin access only

                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )

                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(customAccessDeniedHandler) // Set the access denied point
                )

                .sessionManagement(sess ->sess
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}