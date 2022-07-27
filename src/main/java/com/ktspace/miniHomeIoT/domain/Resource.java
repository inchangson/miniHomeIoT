package com.ktspace.miniHomeIoT.domain;

import com.ktspace.miniHomeIoT.domain.enums.ResourceGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resource {
    private ResourceGroup resourceGroup;
    private String value;
}