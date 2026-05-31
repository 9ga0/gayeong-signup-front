package com.ensharp.gayeongsignup.config;

import com.ensharp.gayeongsignup.customautentication.CustomAccessDeniedHandler;
import com.ensharp.gayeongsignup.customautentication.CustomAuthenticationFailureHandler;
import com.ensharp.gayeongsignup.customautentication.CustomAuthenticationFilter;
import com.ensharp.gayeongsignup.customautentication.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler, AuthenticationEntryPoint authenticationEntryPoint, AuthenticationConfiguration authenticationConfiguration, CustomAccessDeniedHandler accessDeniedHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationConfiguration = authenticationConfiguration;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    // CORS 설정을 위한 메서드
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173"); // 허용할 프론트엔드 origin 설정
        configuration.addAllowedOrigin("https://localhost:5173");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        configuration.setAllowCredentials(true); // 쿠키 허용. 세션ID를 주고받기 위함.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위 설정 적용
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 위에서 설정한 cors
                .csrf(csrf -> csrf.disable()) // 개발 단계에서는 CSRF 비활성화. 원래는 활성화
                //개발 중 편의 설정
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/users/me/**", "/api/v1/sessions/current").hasRole("USER")
//                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                //기본 로그인 필터
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //인증 실패/인가 거부 시 호출될 핸들러 지정
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler));

        //                //로그아웃 처리
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .permitAll()
//                )


        return http.build();
    }

    @Bean
    public CustomAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        // Post api주소(/api/v1/sessions) 요청 받으면 이 필터가 동작
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter("/api/v1/sessions"); //
        //인증 처리할 관리자 설정
        customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        //로그인 성공/실패 로직
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        //인증된 결과를 HttpSession에 저장.
        customAuthenticationFilter.setSecurityContextRepository(
                new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository()
                ));
        return customAuthenticationFilter;
    }

    @Bean //아이디/비밀번호가 맞는지 실제 검증을 수행하는 주체
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //비밀번호 단방향 암호화
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
