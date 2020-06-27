package com.frontEnd.service;

import com.frontEnd.dto.BusinessDto;
import com.frontEnd.dto.BusinessListDto;
import com.frontEnd.entity.Business;

import java.util.List;

public interface BusinessService {

    /***
     * 首页推荐商家
     */
    List<Business> getHotBusiness(String city);

    List<Business> getBusinessBySearch(String city, String key);

    public List<Business> getBySearchAndCate(String city, String category, String key);

    /**
     * 根据主键获取商户dto
     *
     * @param id 主键
     * @return 商户dto
     */
    Business getById(Long id);

    /**
     * 分页搜索商户列表
     *
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表
     */
    List<BusinessDto> searchByPage(BusinessDto businessDto);

    /**
     * 分页搜索商户列表(接口专用)
     *
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表Dto对象
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
