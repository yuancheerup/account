package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;
    private String username;
    private Double balance;
    private Double income;
    private Double pay;
}
