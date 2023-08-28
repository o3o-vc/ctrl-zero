package com.onezero.domain;

import com.mybatisflex.annotation.Column;
import com.onezero.base.ITree;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tree<T extends  Tree<T>> extends Base implements ITree<Long, T> {
    private Long parentId;
    @Column(ignore = true)
    private List<T> children;
    private Integer orderNo;
    private Boolean isLeaf;


}
