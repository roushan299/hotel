package com.booking.hotel.config;

import com.booking.hotel.jwt.JwtAuthenticationFilter;
import com.booking.hotel.model.User;
import com.booking.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelSecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/user/register", "auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .rememberMe().userDetailsService(userDetailsService)
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().deleteCookies("remember-me").and()
                .oauth2Login().loginPage("/login").userInfoEndpoint().oidcUserService(oidcUserService());
//                .httpBasic();

        //http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService users(){
//        UserDetails user1 = User.builder()
//                .username("tony")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("steve")
//                .password(passwordEncoder().encode("nopassword"))
//                .roles("NORMAL")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService(){

        return userRequest -> {
            OidcUserService oidcUserService = new OidcUserService();
            OidcUser oidcUser = oidcUserService.loadUser(userRequest);
            String email = oidcUser.getAttribute("email");
            if(email == null || email.isEmpty()){
                throw new IllegalArgumentException("User not found");
            }

            User user = userRepository.findByUsername(email)
                    .orElseThrow(()
                            -> new UsernameNotFoundException("User not found for email: "+email));
            return new DefaultOidcUser(user.getAuthorities(), userRequest.getIdToken());
        };
    }


}
