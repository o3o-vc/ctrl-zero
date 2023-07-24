package com.onezero.security.access;


import com.onezero.domain.CmdInfo;

import java.util.Set;

/**
 * 命令收集处理类
 */
public class CmdHandler {

    private Set<CmdInfo> cmdSet;

    public CmdHandler(Set<CmdInfo> cmdSet) {
        this.cmdSet = cmdSet;
    }

    public void setCmdSet(Set<CmdInfo> cmdSet) {
        this.cmdSet = cmdSet;
    }

    public Set<CmdInfo> getCmdSet() {
        return this.cmdSet;
    }
}
