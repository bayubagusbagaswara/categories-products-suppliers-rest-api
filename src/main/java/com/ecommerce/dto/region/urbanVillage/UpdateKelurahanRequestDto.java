package com.ecommerce.dto.region.urbanVillage;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateKelurahanRequestDto {

    @NotBlank(message = "Code must not be blank")
    private String code;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Kecamatan ID must not be blank")
    private String kecamatanId;
}
