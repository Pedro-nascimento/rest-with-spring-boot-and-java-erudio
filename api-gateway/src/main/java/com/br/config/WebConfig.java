package com.br.config;

import com.br.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {



        // VIA QUERY PARAM
/*       configurer.favorParameter(true)
               .parameterName("mediaType")
               .ignoreAcceptHeader(true)
               .useRegisteredExtensionsOnly(false)
               .defaultContentType(MediaType.APPLICATION_JSON)
               .mediaType("json",MediaType.APPLICATION_JSON)
               .mediaType("xml",MediaType.APPLICATION_XML);*/


        //VIA HEADER PARAM

        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml",MediaType.APPLICATION_XML)
                .mediaType("xml",MEDIA_TYPE_APPLICATION_YML);

    }
}
