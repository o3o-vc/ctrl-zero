package com.onezero.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EnableEnum implements IEnum<Integer> {
    ENABLE(1, "启用"), DISABLE(0, "禁用");
    @EnumValue
    private final Integer code;
    private final String value;
}
