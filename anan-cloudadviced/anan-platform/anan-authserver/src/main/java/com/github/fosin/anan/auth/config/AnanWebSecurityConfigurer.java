package com.github.fosin.anan.auth.config;

import com.github.fosin.anan.auth.security.AnanUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;

/**
 * @author fosin
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class AnanWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final String INTERNAL_SECRET_KEY = "INTERNAL_SECRET_KEY";
    @Autowired
    private AnanUserDetailsServiceImpl userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/auth/**")
                .authorizeRequests()
                .antMatchers("/auth/login","/auth/logout","/auth/loginAction").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                //除以上路径都需要验证
                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/login?authorization_error=true")
                .and().csrf().disable()
                .logout()
                .clearAuthentication(true)
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .and()
                //http参数中包含一个名为“remember-me”的参数，不管session是否过期，用户记录将会被记保存下来
                .rememberMe()
                //设置起效为2天
                .rememberMeServices(rememberMeServices())
                .and()
                .formLogin()
                //form表单密码参数名
                .usernameParameter("usercode")
                //form表单用户名参数名
                .passwordParameter("password")
                .loginPage("/login.html")
                .loginProcessingUrl("/auth/loginAction")
                .defaultSuccessUrl("/auth/index")
                .failureUrl("/auth/login?error")
//                .and().httpBasic()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(new AnanAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.html",
                "/**/*.css", "/**/*.js", "/**/webjars/**",
                "/**/images/**", "/**/*.jpg", "/**/swagger-resources/**",
                "/**/v2/api-docs");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 返回 RememberMeServices 实例，将用户rememberme信息存在数据库中
     *
     * @return RememberMeServices
     */
    @Bean
    public RememberMeServices rememberMeServices() {
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);

        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices(INTERNAL_SECRET_KEY, userDetailsService, rememberMeTokenRepository);

        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter("remember-me");
        //默认验证时间48小时
        rememberMeServices.setTokenValiditySeconds(172800);
        return rememberMeServices;
    }
}
