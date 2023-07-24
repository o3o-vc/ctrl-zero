package com.onezero.security.access.annotation;


import com.onezero.security.access.CmdAwareProcessor;
import com.onezero.security.access.CmdScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * {@code author} voncho
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CmdScannerRegistrar.class, CmdAwareProcessor.class})
public @interface CmdScan {
    String[] value() default {};
    String[] basePackage() default {};
}
