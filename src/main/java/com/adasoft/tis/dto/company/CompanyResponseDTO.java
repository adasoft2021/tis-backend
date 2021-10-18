package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponseDTO extends BaseResponseDTO<Long> {
    private Long id;
    private String shortname;
    private String name;
    private String companyType;
    private String address;
    private String email;
    private List<String> partners;
}