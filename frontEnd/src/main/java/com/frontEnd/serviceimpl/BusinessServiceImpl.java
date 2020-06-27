package com.frontEnd.serviceimpl;

import com.frontEnd.constant.CategoryConst;
import com.frontEnd.dao.BusinessDao;
import com.frontEnd.dto.BusinessDto;
import com.frontEnd.dto.BusinessListDto;
import com.frontEnd.entity.Business;
import com.frontEnd.entity.Page;
import com.frontEnd.service.BusinessService;
import com.frontEnd.util.CommonUtil;
import com.frontEnd.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String url;

    @Override
    public List<Business> getHotBusiness(String city){
        List<Business> bList = new ArrayList<Business>();
        bList = businessDao.selectHotBusiness(city);
        return bList;
    }

    @Override
    public List<Business> getBusinessBySearch(String city, String key){
        return businessDao.getBusinessBySearch(city, key);
    }

    @Override
    public List<Business> getBySearchAndCate(String city, String category, String key){
        return businessDao.getBySearchAndCate(city, category, key);
    }

    @Override
    public Business getById(Long id) {
        return businessDao.selectById(id);
    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<>();
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        List<Business> list = businessDao.selectByPage(businessForSelect);
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setStar(this.getStar(business));
        }
        return result;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();

        // 组织查询条件
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        // 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中

        if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        // 当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        // 前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        List<Business> list = businessDao.selectLikeByPage(businessForSelect);

        // 经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());

        // 对查询出的结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            // 为兼容前端mumber这个属性
            businessDtoTemp.setNumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
        }

        return result;
    }

    private Long getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0L;
        }
    }
}
