package com.onezero.converter;

import com.onezero.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;

public class IEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {

        if (typeOf(targetType, IEnum.class)) {
            return new StringToIEnum(targetType);
        }
        return new StringToEnum<>(targetType);
    }

    private boolean typeOf(Class<?> source, Class<?> target) {
        Class<?>[] interfaces = source.getInterfaces();
        for (Class<?> clazz : interfaces) {
            if (clazz == target) {
                return true;
            }
        }
        return false;
    }

    private record StringToIEnum<T extends Enum>(Class<T> enumType) implements Converter<String, Enum> {
        @Override
            public Enum convert(String source) {
                for (Enum e : enumType.getEnumConstants()) {
                    if (e instanceof IEnum<?> ie) {
                        if (source.equals(ie.getCode().toString())) {
                            return e;
                        }
                    }
                }
                return null;
            }
        }

    private record StringToEnum<T extends Enum>(Class<T> enumType) implements Converter<String, T> {

        @Nullable
            public T convert(String source) {
                return source.isEmpty() ? null : (T) Enum.valueOf(this.enumType, source.trim());
            }
        }

}
