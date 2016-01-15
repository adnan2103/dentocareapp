package com.dk.dento.care;

import com.dk.dento.care.config.DatabaseConfig;
import com.dk.dento.care.config.TemplateConfig;
import com.dk.dento.care.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Application main config file.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@Import({ DatabaseConfig.class, TemplateConfig.class, WebSecurityConfig.class})
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
