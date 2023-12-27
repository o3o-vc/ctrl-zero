package com.onezero.domain.system;

import com.onezero.domain.Tree;
import lombok.Data;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "data_model")
@Data
public class DataModel extends Tree<DataModel> {
    private String name;
    private String tableName;
    private String description;
}
