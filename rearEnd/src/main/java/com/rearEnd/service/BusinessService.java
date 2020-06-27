package com.rearEnd.service;

import com.rearEnd.dto.BusinessDto;
import com.rearEnd.dto.BusinessListDto;
import com.rearEnd.entity.Business;

import java.util.List;

public interface BusinessService {

    /**
     * 新增
     *
     * @param business 商户对象
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(Business business);

    /**
     * 根据主键获取商户dto
     *
     * @param id 主键
     * @return 商户dto
     */
    BusinessDto getById(Long id);

    /**
     * 分页搜索商户列表
     *
     * @param business 查询条件(包含分页对象)
     * @return 商户列表
     */
    List<BusinessDto> searchByPage(Business business);

    /**
     * 分页搜索商户列表(接口专用)
     *
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表Dto对象
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);

    /**
     * 删除商户
     *
     * @param id
     * @return true:删除成功;false:删除失败
     */
    boolean remove(Long id);

    /**
     * 修改商户
     *
     * @param business
     * @return 是否修改成功：true-成功;fale-失败
     */
    boolean modify(Business business);
}
