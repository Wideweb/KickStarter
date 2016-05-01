package com.kickstarter.configurations;

import com.kickstarter.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "account/register", "account/login").permitAll()
                .antMatchers(HttpMethod.GET, "project/getAll", "/", "/home", "lookups/countries", "lookups/projectTypes").permitAll()
                .antMatchers(HttpMethod.POST, "project/save", "account/logoff").hasAnyRole(Role.AdminRoleName, Role.UserRoleName)
                .antMatchers(HttpMethod.GET, "project/getUserProjects", "project/get").hasAnyRole(Role.AdminRoleName, Role.UserRoleName)
                .antMatchers("/admin/**").hasRole(Role.AdminRoleName)
                .and().csrf().disable();
                //.and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
