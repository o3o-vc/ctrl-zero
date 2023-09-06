package com.onezero.security.access.annotation;

import com.onezero.security.access.CmdInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public interface CmdHandler {
     Collection<CmdInfo> getAll();
     Collection<String> getLogged();
     Collection<String> getPermitted();
}
