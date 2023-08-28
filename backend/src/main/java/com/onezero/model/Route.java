package com.onezero.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onezero.domain.Tree;
import lombok.Data;

import java.util.List;
@Data
public class Route extends Tree<Route> {
    private String name;
    private String path;
    private String component;
    private Meta meta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Route> children;
    public void setChildren(List<Route> children) {
        if (children == null || children.isEmpty()) {
            return ;
        }
        this.children = children;
    }
    @Data
    public static class Meta {
        private String icon;
        private Integer order;
        private String title;
        private Boolean requiresAuth;
        private String i18nTitle;
        private String localIcon;
        private String singleLayout;
        private Boolean keepAlive = true;
        private String href;
    }
}
