package com.onezero.mybatisflex;

import com.mybatisflex.annotation.AbstractInsertListener;
import com.onezero.base.Creatable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * {@code @author} voncho
 * {@code @desc} mybatis-flex insert auto fill
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class CzInsertListener extends AbstractInsertListener<Creatable> {
    @Override
    public Class<Creatable> supportType() {
        return Creatable.class;
    }

    @Override
    public void doInsert(Creatable creatable) {
        creatable.setCreator(1L);
        creatable.setCreated(LocalDateTime.now());
    }

    /*@Override
    public void onInsert(Object o) {
        if (o instanceof Creatable base) {
            base.setCreator(1L);
            base.setCreated(LocalDateTime.now());

        } else {
            log.error("实体填充创建信息需要实现: Creatable接口 ->" + o.toString());
        }
    }*/
}
