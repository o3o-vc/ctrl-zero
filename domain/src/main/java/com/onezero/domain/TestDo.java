package com.onezero.domain;

import lombok.Data;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "test")
@Data
public class TestDo extends Base {
    private String name;
    private String mark;
}
