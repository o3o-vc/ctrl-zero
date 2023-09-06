package com.onezero.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CmdEnum {
    GROUP("分组"), CMD("命令");
    private final String value;
}
