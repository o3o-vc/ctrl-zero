package com.onezero.security.access.annotation;

import com.onezero.security.access.CmdHandlerImpl;
import org.springframework.beans.factory.Aware;

@FunctionalInterface
public interface CmdHandlerAware extends Aware {
    void setCmdHandler(CmdHandlerImpl cmdHandler);
}
