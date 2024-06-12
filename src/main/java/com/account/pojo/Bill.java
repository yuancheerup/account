package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer id;
    private String category;
    private String type;
    private Double money;
    private String wayType;
    private String createTime;
    private Integer userId;
}
