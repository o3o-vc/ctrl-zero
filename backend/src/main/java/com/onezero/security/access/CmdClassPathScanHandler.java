package com.onezero.security.access;

import com.onezero.enums.CmdEnum;
import com.onezero.security.access.annotation.Cmd;
import com.onezero.security.access.annotation.CmdHandler;
import com.onezero.security.access.annotation.Group;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
* {@code @Description:} CmdScan 包扫描详细实现
* {@code @Author:} voncho
* {@code @Date:} 2022/12/27 11:57
 */
public class CmdClassPathScanHandler extends ClassPathBeanDefinitionScanner {

    public CmdClassPathScanHandler(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    @NonNull
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        //添加过滤条件，这里是只添加的注解才会被扫描到
        addIncludeFilter(new AnnotationTypeFilter(Group.class));
        //调用spring的扫描
        Set<CmdInfo> treeCmd = new LinkedHashSet<>();
        Set<String> logged = new LinkedHashSet<>();
        Set<String> permitted = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                if (candidate instanceof ScannedGenericBeanDefinition scannedCandidate) {
                    String className = candidate.getBeanClassName();
                    AnnotationMetadata metadata = scannedCandidate.getMetadata();
                    MergedAnnotation<RequestMapping> requestMappingAnnotation = metadata.getAnnotations().get(RequestMapping.class);
                    String prefix = "";
                    if (requestMappingAnnotation.isPresent()) {
                        prefix = firstOfAnnotationParams(requestMappingAnnotation);
                    }
                    MergedAnnotation<Group> groupAnnotation = metadata.getAnnotations().get(Group.class);
                    CmdInfo groupCmd = new CmdInfo();
                    assert className != null;
                    groupCmd.setId(className.replace(basePackage + ".", ""));
                    groupCmd.setParentId(CmdEnum.GROUP.name());
                    groupCmd.setCode(groupCmd.getParentId() + ":" + groupCmd.getId());
                    groupCmd.setType(CmdEnum.GROUP);
                    groupCmd.setName(groupAnnotation.getString("value"));
                    groupCmd.setIsLeaf(false);
                    Set<MethodMetadata> annotatedMethods = metadata.getAnnotatedMethods(Cmd.class.getName());
                    if (!CollectionUtils.isEmpty(annotatedMethods)) {
                        List<CmdInfo> cmdList = new ArrayList<>();
                        for (MethodMetadata methodMetadata : annotatedMethods) {
                            MergedAnnotations annotations = methodMetadata.getAnnotations();
                            MergedAnnotation<Cmd> cmdAnnotation = annotations.get(Cmd.class);
                            MergedAnnotation<RequestMapping> methodRequest = annotations.get(RequestMapping.class);
                            CmdInfo cmdInfo = new CmdInfo();
                            cmdInfo.setParentId(groupCmd.getId());
                            String path = firstOfAnnotationParams(methodRequest);
                            cmdInfo.setId((prefix + ">" + path).replace("/", ""));
                            cmdInfo.setCode(cmdInfo.getParentId() + ":" + cmdInfo.getId());
                            cmdInfo.setName(cmdAnnotation.getString("value"));
                            cmdInfo.setType(CmdEnum.CMD);
                            cmdInfo.setLogged(cmdAnnotation.getBoolean("logged"));
                            cmdInfo.setPermitted(cmdAnnotation.getBoolean("permitted"));
                            cmdInfo.setIsLeaf(true);
                            cmdInfo.setChildren(List.of());
                            cmdList.add(cmdInfo);
                            if (cmdInfo.getLogged()) {
                                logged.add("/" + cmdInfo.getId().replace(">", "/"));
                            }
                            if (cmdInfo.getPermitted()) {
                                permitted.add("/" + cmdInfo.getId().replace(">", "/"));
                            }
                        }
                        groupCmd.setChildren(cmdList);
                    }
                    if (!groupCmd.getChildren().isEmpty()) {
                        treeCmd.add(groupCmd);
                    }
                }
            }
        }
        RootBeanDefinition beanDefinition = new RootBeanDefinition(CmdHandler.class, () -> new CmdHandlerImpl(treeCmd, logged, permitted));
        beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        assert getRegistry() != null;
        getRegistry().registerBeanDefinition("cmdHandler", beanDefinition);
        return Set.of(new BeanDefinitionHolder(beanDefinition, "cmdHandler"));
    }


    private String firstOfAnnotationParams(MergedAnnotation<RequestMapping> ma) {
        String[] values = ma.getStringArray("value");
        if (values.length > 0) {
            return values[0];
        }
        return "";
    }

    public static void main(String[] args) {

    }
}
