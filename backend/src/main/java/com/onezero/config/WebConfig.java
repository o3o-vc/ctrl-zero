package com.onezero.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.onezero.converter.IEnumConverterFactory;
import com.onezero.core.constant.PatternConst;
import com.onezero.enums.IEnum;
import com.onezero.spring.converter.LocalDateConverter;
import com.onezero.spring.converter.LocalDateTimeConverter;
import com.onezero.spring.converter.LocalTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer {

    //mvc params
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.removeConvertible(String.class, Enum.class);
        registry.addConverterFactory(new IEnumConverterFactory());
        registry.addConverter(new LocalDateTimeConverter());
        registry.addConverter(new LocalDateConverter());
        registry.addConverter(new LocalTimeConverter());
    }

    // mvc return
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(PatternConst.DATETIME_FORMAT)));
        simpleModule.addSerializer(new LocalDateSerializer(DateTimeFormatter.ofPattern(PatternConst.DATE_FORMAT)));
        simpleModule.addSerializer(new LocalTimeSerializer(DateTimeFormatter.ofPattern(PatternConst.TIME_FORMAT)));
        simpleModule.addSerializer(IEnum.class, new JsonSerializer<>() {
            @Override
            public void serialize(IEnum iEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                if (iEnum.getCode() instanceof Integer intCode) {
                    jsonGenerator.writeNumberField("code", intCode);
                } else {
                    jsonGenerator.writeStringField("code", iEnum.getCode().toString());
                }

                jsonGenerator.writeStringField("value", iEnum.getValue());
                jsonGenerator.writeEndObject();
            }
        });
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        converters.add(0, jackson2HttpMessageConverter);
    }

}
