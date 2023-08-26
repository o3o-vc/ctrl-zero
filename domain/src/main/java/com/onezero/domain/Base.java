package com.onezero.domain;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.onezero.base.Creatable;
import com.onezero.base.IBase;
import com.onezero.base.Modifiable;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Base implements IBase<Long>, Creatable<Long, LocalDateTime>, Modifiable<Long, LocalDateTime> {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long creator;
    private LocalDateTime created;
    private Long modifier;
    private LocalDateTime modified;
    @Column("creator_name")
    private String creatorName;
    @Column("modifier_name")
    private String modifierName;
}
