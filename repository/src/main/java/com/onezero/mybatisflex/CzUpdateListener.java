package com.onezero.mybatisflex;

import com.mybatisflex.annotation.AbstractUpdateListener;
import com.onezero.base.Modifiable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * {@code @author} voncho
 * {@code @desc} mybatis-flex update auto fill
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class CzUpdateListener extends AbstractUpdateListener<Modifiable> {
    @Override
    public Class<Modifiable> supportType() {
        return Modifiable.class;
    }

    @Override
    public void doUpdate(Modifiable modifiable) {
        modifiable.setModified(LocalDateTime.now());
        modifiable.setModifier(1L);
    }
    /*@Override
    public void onUpdate(Object o) {
        if (o instanceof Modifiable modify) {
            modify.setModified(LocalDateTime.now());
            modify.setModifier(1L);
        } else {
            log.error("实体填充修改信息需要实现: Modifiable接口 ->" + o.toString());
        }
    }*/
}
