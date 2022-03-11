package com.restful.dto.provinsi;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProvinsiRequestDto {

    @NotBlank(message = "Provinsi code must not be blank")
    private String code;

    @NotBlank(message = "Provinsi name must not be blank")
    private String name;
}
