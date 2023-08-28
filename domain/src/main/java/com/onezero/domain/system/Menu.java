package com.onezero.domain.system;

import com.mybatisflex.annotation.Table;
import com.onezero.domain.Tree;
import lombok.Data;

@Table("menu")
@Data
public class Menu extends Tree<Menu> {
    private String name;
    private String path;
    private String component;
    private String title;
    private String icon;
    private Boolean requiresAuth;
    private String i18nTitle;
    private String localIcon;
    private String href;
    private String singleLayout;
    private Boolean keepAlive;
}
