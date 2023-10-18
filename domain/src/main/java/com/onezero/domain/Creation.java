package com.onezero.domain;


import com.onezero.base.Creatable;
import com.onezero.base.IBase;
import com.onezero.convert.Create;
import lombok.Data;
import org.beetl.sql.annotation.entity.AutoID;

import java.time.LocalDateTime;
@Data
public class Creation implements IBase<Long>, Creatable<Long, LocalDateTime> {
    @AutoID
    private Long id;
    @Create
    private Long creator;
    @Create
    private LocalDateTime created;
    @Create
    private String creatorName;

}
