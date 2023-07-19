package com.onezero.domain;

import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("test")
@Data
public class TestDo extends Base {
    private String name;
    private String mark;
}
