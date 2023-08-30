package com.onezero.mybatisflex;

import com.mybatisflex.annotation.AbstractInsertListener;
import com.onezero.base.Creatable;
import com.onezero.domain.Base;
import com.onezero.security.SecurityUser;
import com.onezero.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

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
        SecurityUser userDetails = SecurityUtil.getUserDetails();
        if (Objects.nonNull(userDetails)) {
            creatable.setCreator(userDetails.user().getId());
            creatable.setCreated(LocalDateTime.now());
            if (creatable instanceof Base base) {
                base.setCreatorName(userDetails.user().getName());
            }
        } else {
            creatable.setCreator(0L);
            creatable.setCreated(LocalDateTime.now());
            if (creatable instanceof Base base) {
                base.setCreatorName("系统调用");
            }
        }

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
