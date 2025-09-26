package dev.caobaoqi6040.backend.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * SpringSecurityConfiguration
 *
 * @author caobaoqi6040
 * @since 2025/9/23 15:57
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {

	private final CorsConfigurationSource corsConfigurationSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public RequestMatcher publicEndPointMatcher() {
		return new OrRequestMatcher(
			// dev
			PathPatternRequestMatcher.withDefaults().matcher("/api/v1/users/**"),
			// prod
			PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/auth/sign-in"),
			PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/auth/sign-up"),
			PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/v3/api-docs/**"),
			PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/swagger-ui/**"),
			PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/swagger-ui.html"),
			PathPatternRequestMatcher.withDefaults().matcher("/error")
		);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		var restfulAuthenticationEntryPointHandler = new RestfulAuthenticationEntryPointHandler();
		/*
		<Stateless API CSRF protection>
		http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		*/
		http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource));
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(publicEndPointMatcher()).permitAll()
			.anyRequest().authenticated());
		http.exceptionHandling((exceptionHandling) -> exceptionHandling
			.accessDeniedHandler(restfulAuthenticationEntryPointHandler)
			.authenticationEntryPoint(restfulAuthenticationEntryPointHandler));
		http.sessionManagement(session -> session
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}
