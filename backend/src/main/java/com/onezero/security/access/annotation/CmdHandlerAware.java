package com.onezero.security.access.annotation;

import com.onezero.security.access.CmdHandler;
import org.springframework.beans.factory.Aware;

@FunctionalInterface
public interface CmdHandlerAware extends Aware {
    void setCmdHandler(CmdHandler cmdHandler);
}
