package com.example.blog.ConfigSecurity;

import com.example.blog.Service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ConfigrationSecurity  {

private final MyUserDetailsService myUserDetailsService;

@Bean
public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
}

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/v1/api/user/register").permitAll()
                .requestMatchers("/v1/api/user/get").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/user/update/{id}"
                        ,"/v1/api/user/delete/{id}","/v1/api/blog/get"
                        ,"/v1/api/blog/add","/v1/api/blog/update/{blogId}" ,
                        "/v1/api/blog/delete/{blogId}","/v1/api/blog/get/{blogId}",
                        "/v1/api/blog/get-title/{title}").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/v1/api/logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .httpBasic();

        return httpSecurity.build();

    }

}
