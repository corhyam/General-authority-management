package com.jier.admin.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-31 23:53
 **/
@Data
public class SelectTree {


    private int id;
    private String name;
    private boolean open;
    private boolean checked;
    private List<SelectTree> children;


}
