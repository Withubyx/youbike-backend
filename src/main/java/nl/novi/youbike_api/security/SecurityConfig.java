package nl.novi.youbike_api.security;

import nl.novi.youbike_api.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider(udService);
        auth.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(auth);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/bike-companies/**", "/cyclists/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/bike-rides/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/bikes/**", "/bike-comments/**").hasRole("CYCLIST")
                        .requestMatchers(HttpMethod.PUT, "/bike-rides/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/bike-companies/**").hasRole("BIKE_COMPANY")
                        .requestMatchers(HttpMethod.PUT, "/cyclists/**", "/bikes/**", "/bike-comments/**").hasRole("CYCLIST")
                        .requestMatchers(HttpMethod.PATCH, "/bike-companies/**").hasRole("BIKE_COMPANY")
                        .requestMatchers(HttpMethod.PATCH, "/cyclists/**").hasRole("CYCLIST")
                        .requestMatchers(HttpMethod.DELETE, "/bike-rides/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/bike-companies/**").hasRole("BIKE_COMPANY")
                        .requestMatchers(HttpMethod.DELETE, "/cyclists/**", "/bikes/**", "/bike-comments/**").hasRole("CYCLIST")
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}