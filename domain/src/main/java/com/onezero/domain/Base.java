package com.onezero.domain;



import com.onezero.convert.Create;
import com.onezero.base.Creatable;
import com.onezero.base.IBase;
import com.onezero.base.Modifiable;
import com.onezero.convert.Modify;
import lombok.Data;
import org.beetl.sql.annotation.entity.AutoID;
import org.beetl.sql.annotation.entity.Column;

import java.time.LocalDateTime;
@Data

public class Base implements IBase<Long>, Creatable<Long, LocalDateTime>, Modifiable<Long, LocalDateTime> {
    @AutoID
    private Long id;
    @Create
    private Long creator;
    @Create
    private LocalDateTime created;
    @Modify
    private Long modifier;
    @Modify
    private LocalDateTime modified;
    @Create
    @Column("creator_name")
    private String creatorName;
    @Modify
    @Column("modifier_name")
    private String modifierName;
}
