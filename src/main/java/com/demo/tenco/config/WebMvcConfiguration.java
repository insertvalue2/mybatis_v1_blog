package com.demo.tenco.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.tenco.filter.UriTrackingFilter;
import com.demo.tenco.intercepter.LoginIntercepter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
    @Bean
    public FilterRegistrationBean<?> filterBean() {
    
        FilterRegistrationBean<UriTrackingFilter> registrationBean = new FilterRegistrationBean(new UriTrackingFilter());
        registrationBean.setOrder(Integer.MIN_VALUE); //필터 여러개 적용 시 순번
        registrationBean.addUrlPatterns("/api/*"); // api로 시작하는  URI 포함
        return registrationBean;
    }
    
    // 인터셉터 등록 예정 
    @Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginIntercepter())
		.addPathPatterns("/auth/**", "/api/**");
//		registry.addInterceptor(new HelloIntercepter())
//		.addPathPatterns("/hello/**");
		
	}
}
