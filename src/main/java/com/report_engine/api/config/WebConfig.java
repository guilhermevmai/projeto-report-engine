package com.report_engine.api.config;

import com.report_engine.api.config.converters.StringToReadFilesStrategiesConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToReadFilesStrategiesConverter());
    }
}
