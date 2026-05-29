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
        configuration.addAllowedOrigin("http://localhost:8080"); // 허용할 프론트엔드 origin 설정
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 쿠키 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위 설정 적용
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                //개발 중 편의 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/me", "/api/v1/sessions/current").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(config ->config
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                //로그인 페이지 설정.
//                .formLogin(login -> login
//                                .loginPage("http://localhost:8080/").permitAll()  // 커스텀 로그인 페이지
//                                //.loginProcessingUrl("/loginProcess") //제출된 폼 데이터로 post요청할 url
//                                .usernameParameter("email") //email을 로그인할때 사용할 것이므로 변경.
//                                .passwordParameter("password")
//
////                                .successHandler(customAuthenticationSuccessHandler)
////                                .failureHandler(customAuthenticationFailureHandler)
//
//                                .defaultSuccessUrl("/home", true) //로그인 성공시 이동 url
//                                .permitAll()
//                )
//                //로그아웃 처리
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .permitAll()
//                )
                .csrf(csrf -> csrf.disable()) // 개발 단계에서는 CSRF 비활성화. 원래는 활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));    // cors 설정 코드 추가

        return http.build();
    }

//    //폼 로그링 대신 Rest API를 통해 사용자 인증
//    //다음 사용하여 맞춤형 인증 시나리오 게시
//    @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//        providerManager.setEraseCredentialsAfterAuthentication(false);
//
//        return providerManager;
//    }

//    @Bean
//    public UserDetailsService userdetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("email")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public CustomAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter("/api/login"); //로그인할 페이지
        customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        customAuthenticationFilter.setSecurityContextRepository(
                new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository()
                ));
        return customAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
