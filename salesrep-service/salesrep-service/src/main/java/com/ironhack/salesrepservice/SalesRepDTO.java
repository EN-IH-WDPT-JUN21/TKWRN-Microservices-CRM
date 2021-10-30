package com.ironhack.salesrepservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepDTO {

    private Long id;

    private String repName;

    public SalesRepDTO(String repName) {
        this.repName = repName;
    }
}
