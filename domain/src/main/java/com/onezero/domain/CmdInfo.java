package com.onezero.domain;

import com.onezero.base.ITree;
import lombok.Data;

import java.util.List;

@Data
public class CmdInfo implements ITree<String, CmdInfo> {

    private String id;
    private String parentId;
    private List<CmdInfo> children;
    private Integer orderNo;
    private Boolean isLeaf;
    private String code;
    private String name;
    private Boolean logged = false;



}
