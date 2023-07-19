package com.onezero.domain;


import com.onezero.base.Creatable;
import com.onezero.base.IBase;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Creation implements IBase<Long>, Creatable<Long, LocalDateTime> {
    private Long id;
    private Long creator;
    private LocalDateTime created;

}
