package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDto;
import com.ecommerce.dto.provinsi.*;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;
import com.ecommerce.service.region.ProvinceService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinceDTO> createProvinsi(@RequestBody CreateProvinceRequestDTO createCategoryRequest) {
        final ProvinceDTO provinsiResponse = provinceService.createProvinsi(createCategoryRequest);
        return WebResponseDto.<ProvinceDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @GetMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinceDTO> getProvinsiById(@PathVariable("idProvinsi") String id) throws ProvinsiNotFoundException {
        final ProvinceDTO provinsiResponse = provinceService.getProvinsiById(id);
        return WebResponseDto.<ProvinceDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListProvinceResponseDTO> getAllProvinsi(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "searchBy", defaultValue = AppConstants.DEFAULT_SEARCH_BY, required = false) String searchBy,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProvinceRequestDTO listProvinsiRequest = new ListProvinceRequestDTO();
        listProvinsiRequest.setPageNo(pageNo);
        listProvinsiRequest.setPageSize(pageSize);
        listProvinsiRequest.setSortBy(sortBy);
        listProvinsiRequest.setSortDir(sortDir);

        final ListProvinceResponseDTO allProvinsiResponse = provinceService.getAllProvinsi(listProvinsiRequest);
        return WebResponseDto.<ListProvinceResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allProvinsiResponse)
                .build();
    }

    @PutMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinceDTO> updateProvinsi(@PathVariable("idProvinsi") String id, UpdateProvinceRequestDTO updateProvinsiRequest) throws ProvinsiNotFoundException {
        final ProvinceDTO provinsiResponse = provinceService.updateProvinsi(id, updateProvinsiRequest);
        return WebResponseDto.<ProvinceDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @DeleteMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteProvinsi(@PathVariable("idProvinsi") String id) throws ProvinsiNotFoundException {
        provinceService.deleteProvinsi(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinceDTO> getProvinsiByName(@RequestParam("name") String name) throws ProvinsiNotFoundException {
        final ProvinceDTO provinsiResponse = provinceService.getProvinsiByName(name);
        return WebResponseDto.<ProvinceDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<ProvinceDTO>> getProvinsiByNameContaining(@RequestParam("name") String name) {
        final List<ProvinceDTO> provinsiResponseList = provinceService.getProvinsiByNameContains(name);
        return WebResponseDto.<List<ProvinceDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinceDTO> getProvinsiByCode(@RequestParam("code") String code) throws ProvinsiNotFoundException {
        final ProvinceDTO provinsiResponse = provinceService.getProvinsiByCode(code);
        return WebResponseDto.<ProvinceDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }
}
