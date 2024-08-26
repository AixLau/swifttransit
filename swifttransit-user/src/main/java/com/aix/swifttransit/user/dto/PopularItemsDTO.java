package com.aix.swifttransit.user.dto;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class PopularItemsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Parameter(name = "物品名称")
    private String itemName;

    @Parameter(name = "物品类型")
    private String itemType;
}

