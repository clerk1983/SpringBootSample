package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * セキュリティの対象外を設定
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ非適応
        web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**");
    }

    /**
     * セキュリティの各種設定
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ログイン不要ページの設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/signup").permitAll()
                .anyRequest().authenticated();

        // ログイン処理
        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/list", true);

        // CSRF対策を無効
        http.csrf().disable();

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        final var encoder = passwordEncoder();
        // インメモリ認証
        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(encoder.encode("user"))
                    .roles("GENERAL")
                .and()
                .withUser("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN");
    }


}
