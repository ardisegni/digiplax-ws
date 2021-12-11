package org.worldemunah.digiplax.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * User: Ariel
 * Date: 4/11/2019
 */
@Configuration
public class WebServiceConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**", "/")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/index.html")
                .setCachePeriod(3600 * 24);
    }
}
