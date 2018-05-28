package com.autobestinfo.dev.core.security;

import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.autobestinfo.dev.core.security.SecurityUtils.SIGN_UP_URL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Determines if Spring Security’s pre post annotations [@PreAuthorize,@PostAuthorize,..] should be enabled.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService =  userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // A method where we can define which resources are public and which are secured.
    // In our case, we set the SIGN_UP_URL endpoint as being public and everything else as being secured.
    // We also configure CORS (Cross-Origin Resource Sharing) support through http.cors()
    // and we add a custom security filter in the Spring Security filter chain.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //A method where we defined a custom implementation of UserDetailsService to load user-specific data in the security framework.
    //We have also used this method to set the encrypt method used by our application (BCryptPasswordEncoder).
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    //A method where we can allow/restrict our CORS support.
    //In our case we left it wide open by permitting requests from any source (/**).
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}