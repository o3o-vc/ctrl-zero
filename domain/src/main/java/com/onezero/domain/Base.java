package com.onezero.domain;



import com.onezero.convert.Create;
import com.onezero.base.Creatable;
import com.onezero.base.IBase;
import com.onezero.base.Modifiable;
import com.onezero.convert.Modify;
import lombok.Data;
import org.beetl.sql.annotation.entity.AutoID;
import org.beetl.sql.annotation.entity.Column;
import org.beetl.sql.annotation.entity.InsertIgnore;
import org.beetl.sql.annotation.entity.UpdateIgnore;

import java.time.LocalDateTime;
@Data

public class Base implements IBase<Long>, Creatable<Long, LocalDateTime>, Modifiable<Long, LocalDateTime> {
    @AutoID
    private Long id;
    @Create
    @UpdateIgnore
    private Long creator;
    @Create
    @UpdateIgnore
    private LocalDateTime created;
    @Modify
    @InsertIgnore
    private Long modifier;
    @Modify
    @InsertIgnore
    private LocalDateTime modified;
    @Create
    @Column("creator_name")
    @UpdateIgnore
    private String creatorName;
    @Modify
    @Column("modifier_name")
    @InsertIgnore
    private String modifierName;
}
