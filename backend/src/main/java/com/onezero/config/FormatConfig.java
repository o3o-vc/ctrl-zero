package com.onezero.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.onezero.core.constant.PatternConst;
import com.onezero.spring.converter.LocalDateConverter;
import com.onezero.spring.converter.LocalDateTimeConverter;
import com.onezero.spring.converter.LocalTimeConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class FormatConfig {

    // controller parameter
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializers(
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(PatternConst.DATETIME_FORMAT)),
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(PatternConst.DATE_FORMAT)),
                        new LocalTimeSerializer(DateTimeFormatter.ofPattern(PatternConst.TIME_FORMAT))
                )
                .deserializers(
                        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(PatternConst.DATETIME_FORMAT)),
                        new LocalDateDeserializer(DateTimeFormatter.ofPattern(PatternConst.DATE_FORMAT)),
                        new LocalTimeDeserializer(DateTimeFormatter.ofPattern(PatternConst.TIME_FORMAT))
                )
                //Long 在前台超过 18 位 丢失精度 修改为只针对id
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .serializerByType(Long.class, ToStringSerializer.instance);
    }

    // controller return result
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new LocalDateTimeConverter();
    }
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new LocalDateConverter();
    }

    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new LocalTimeConverter();
    }
}
