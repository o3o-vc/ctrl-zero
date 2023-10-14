package com.onezero.domain;

import com.onezero.base.ITree;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.annotation.entity.InsertIgnore;
import org.beetl.sql.annotation.entity.UpdateIgnore;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tree<T extends  Tree<T>> extends Base implements ITree<Long, T> {
    private Long parentId;
    @InsertIgnore
    @UpdateIgnore
    private List<T> children;
    private Integer orderNo;
    private Boolean isLeaf;


}
