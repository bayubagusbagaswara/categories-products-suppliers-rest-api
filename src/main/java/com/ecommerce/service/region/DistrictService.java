package com.ecommerce.service.region;

import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.exception.ProvinsiNotFoundException;

import java.util.List;

public interface DistrictService {

    KotaResponseDto createKota(CreateKotaRequestDto createKotaRequest) throws ProvinsiNotFoundException;

    KotaResponseDto getKotaById(String id) throws KotaNotFoundException;

    ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequest);

    KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequest) throws KotaNotFoundException, ProvinsiNotFoundException;

    void deleteKota(String id) throws KotaNotFoundException;

    KotaResponseDto getKotaByName(String name) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByNameContains(String name);

    KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByProvinsiId(String provinsiId);
}
