package com.onezero.mybatisflex;

import com.mybatisflex.annotation.AbstractUpdateListener;
import com.onezero.base.Modifiable;
import com.onezero.domain.Base;
import com.onezero.security.SecurityUser;
import com.onezero.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

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
        SecurityUser userDetails = SecurityUtil.getUserDetails();
        if (Objects.nonNull(userDetails)) {
            modifiable.setModified(LocalDateTime.now());
            modifiable.setModifier(userDetails.user().getId());
            if (modifiable instanceof Base base) {
                base.setModifierName(userDetails.user().getName());
            }
        } else {
            modifiable.setModified(LocalDateTime.now());
            modifiable.setModifier(0L);
            if (modifiable instanceof Base base) {
                base.setModifierName("系统调用");
            }
        }

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
