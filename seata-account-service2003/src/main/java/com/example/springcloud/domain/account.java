package com.example.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class account {
    private Long id;
    private Long user_id;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;

}
