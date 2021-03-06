package com.ecommerce.service.impl;

import com.ecommerce.entity.region.Province;
import com.ecommerce.mapper.WilayahMapper;
import com.ecommerce.dto.provinsi.*;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;
import com.ecommerce.repository.ProvinsiRepository;
import com.ecommerce.service.region.ProvinceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinsiRepository provinsiRepository;
    private final WilayahMapper wilayahMapper;

    public ProvinceServiceImpl(ProvinsiRepository provinsiRepository, WilayahMapper wilayahMapper) {
        this.provinsiRepository = provinsiRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public ProvinceDTO createProvinsi(CreateProvinceRequestDTO createProvinsiRequest) {
        Province province = new Province();
        province.setCode(createProvinsiRequest.getCode());
        province.setName(createProvinsiRequest.getName());
        province.setCreatedDate(LocalDateTime.now());
        provinsiRepository.save(province);
        return wilayahMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ProvinceDTO getProvinsiById(String id) throws ProvinsiNotFoundException {
        Province province = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        return wilayahMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ListProvinceResponseDTO getAllProvinsi(ListProvinceRequestDTO listProvinsiRequest) {

        // ada parameter searchBy
        Integer pageNo = listProvinsiRequest.getPageNo();
        Integer pageSize = listProvinsiRequest.getPageSize();
        String sortBy = listProvinsiRequest.getSortBy();
        String sortDir = listProvinsiRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // intinya disini
        // karena findAll maka kita akan mendapatkan semua data nya
        // tapi kita ingin find ini bergantung terhadap nilai dari properti searchBy
        // jadi pencarian data berdasarkan query where ...


        // if (searchByName.isExist) {
        // maka provinsiRepository.findAllByName
        // }
        Page<Province> provinsiPage = provinsiRepository.findAll(pageable);


        List<Province> provinceList = provinsiPage.getContent();

        List<ProvinceDTO> provinsiResponseList = wilayahMapper.mapToProvinsiResponseList(provinceList);

        ListProvinceResponseDTO listProvinsiResponse = new ListProvinceResponseDTO();
        listProvinsiResponse.setProvinsiList(provinsiResponseList);
        listProvinsiResponse.setPageNo(provinsiPage.getNumber());
        listProvinsiResponse.setPageSize(provinsiPage.getSize());
        listProvinsiResponse.setTotalElements(provinsiPage.getTotalElements());
        listProvinsiResponse.setTotalPages(provinsiPage.getTotalPages());
        listProvinsiResponse.setLast(provinsiPage.isLast());
        return listProvinsiResponse;
    }

    @Override
    public ProvinceDTO updateProvinsi(String id, UpdateProvinceRequestDTO updateProvinsiRequest) throws ProvinsiNotFoundException {
        Province province = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        province.setCode(updateProvinsiRequest.getCode());
        province.setName(updateProvinsiRequest.getName());
        province.setUpdatedDate(LocalDateTime.now());
        provinsiRepository.save(province);
        return wilayahMapper.mapToProvinsiResponse(province);
    }

    @Override
    public void deleteProvinsi(String id) throws ProvinsiNotFoundException {
        final Province province = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + id + "] not found"));
        provinsiRepository.delete(province);
    }

    @Override
    public ProvinceDTO getProvinsiByName(String name) throws ProvinsiNotFoundException {
        Province province = provinsiRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi name [" + name + "] not found"));
        return wilayahMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ProvinceDTO getProvinsiByCode(String code) throws ProvinsiNotFoundException {
        Province province = provinsiRepository.findAllByCode(code).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi code [" + code + "] not found"));
        return wilayahMapper.mapToProvinsiResponse(province);
    }

    @Override
    public List<ProvinceDTO> getProvinsiByNameContains(String name) {
        List<Province> provinceList = provinsiRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToProvinsiResponseList(provinceList);
    }

}
