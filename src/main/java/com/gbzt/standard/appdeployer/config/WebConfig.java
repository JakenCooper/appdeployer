package com.gbzt.standard.appdeployer.config;

import com.gbzt.standard.appdeployer.listener.EncodeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.gbzt.standard.appdeployer.controller"
    ,"com.gbzt.standard.appdeployer.listener"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private EncodeInterceptor encodeInterceptor;

    @Bean(name="defaultViewResolver")
    public InternalResourceViewResolver getDefaultViewResolver(){
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix("/WEB-INF/views/");
        irv.setSuffix(".jsp");
        return irv;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver getDefaultMultipartViewResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(500*1024*1024);
        resolver.setMaxInMemorySize(20*1024*1024);
        return resolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        converters.add(jackson2Converter);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(encodeInterceptor);
    }
}
