package com.hieu.demotestspring.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Account {
    private String number;

    private String name;

    private Long money;
}
