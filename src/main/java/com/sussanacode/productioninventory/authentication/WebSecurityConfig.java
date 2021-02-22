package com.sussanacode.productioninventory.authentication;

import com.sussanacode.productioninventory.service.StaffDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public DataSource dataSource;


    @Bean
    public UserDetailsService userDetailsService() {
        return new StaffDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    //configure spring securities for roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    //configure authentication and authorisation for the application
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/staffs", "/products", "/css/*","js/*,", "/webjars/**")
                .permitAll()
                .antMatchers("/category/delete/**").hasAnyAuthority("Admin")
                .antMatchers("/productCategory/new").hasAnyAuthority("Admin", "Supervisor")
                .antMatchers("/products/delete/**").hasAuthority("Admin")
                .antMatchers("/product/new").hasAnyAuthority("Admin","Supervisor")
                .antMatchers("/products/edit/**").hasAnyAuthority("Admin","Supervisor")
                .antMatchers("/staffs/delete/**").hasAuthority("Admin")
                .antMatchers("/staffs/edit/**").hasAnyAuthority("Admin","Supervisor")
                .antMatchers("/staffs/new").hasAnyAuthority("Admin","Supervisor")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessdenied");

    }


//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/resources/**", "/static/**");
//    }
}
