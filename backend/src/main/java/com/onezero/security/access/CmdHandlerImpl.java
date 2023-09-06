package com.onezero.security.access;


import com.onezero.security.access.annotation.CmdHandler;

import java.util.Set;

/**
 * 命令收集处理类
 */
public class CmdHandlerImpl implements CmdHandler {

    private Set<CmdInfo> all;
    private Set<String> logged;
    private Set<String> permitted;

    public CmdHandlerImpl(Set<CmdInfo> all, Set<String> logged, Set<String> permitted) {
        this.all = all;
        this.logged = logged;
        this.permitted = permitted;
    }

    public Set<CmdInfo> getAll() {
        return all;
    }

    public Set<String> getLogged() {
        return logged;
    }

    public Set<String> getPermitted() {
        return permitted;
    }

}
