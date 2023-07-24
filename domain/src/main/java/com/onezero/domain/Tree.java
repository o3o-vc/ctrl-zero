package com.onezero.domain;

import com.onezero.base.ITree;
import lombok.Data;

import java.util.List;

@Data
public class Tree extends Base implements ITree<Long, Tree> {
    private Long parentId;
    private List<Tree> children;
    private Integer orderNo;
    private Boolean isLeaf;
}
