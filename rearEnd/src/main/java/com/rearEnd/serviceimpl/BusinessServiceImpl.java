package com.rearEnd.serviceimpl;

import com.rearEnd.constant.CategoryConst;
import com.rearEnd.dao.BusinessDao;
import com.rearEnd.dto.BusinessDto;
import com.rearEnd.dto.BusinessListDto;
import com.rearEnd.entity.Business;
import com.rearEnd.entity.Page;
import com.rearEnd.service.BusinessService;
import com.rearEnd.util.CommonUtil;
import com.rearEnd.util.FileUtil;
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
    public BusinessDto getById(Long id) {
        BusinessDto result = new BusinessDto();
        Business business = businessDao.selectById(id);
        BeanUtils.copyProperties(business, result);
        result.setImg(savePath + business.getImgFileName());

        result.setStar(this.getStar(business));
        System.out.println("img:" + result.getImg());
        return result;
    }

    @Override
    public List<BusinessDto> searchByPage(Business business) {
        List<BusinessDto> result = new ArrayList<>();
        Business businessForSelect = new Business();
        List<Business> list = businessDao.selectByPage(business);
        for (Business b : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(b, businessDtoTemp);
            businessDtoTemp.setImg(url + b.getImgFileName());
            businessDtoTemp.setStar(this.getStar(b));
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
            businessDtoTemp.setImg(url + business.getImgFileName());
            // 为兼容前端mumber这个属性
            businessDtoTemp.setNumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
        }

        return result;
    }

    @Override
    public boolean add(Business business) {
        return businessDao.insert(business) > 0 ? true : false;
    }

    private Long getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0L;
        }
    }

    public boolean remove(Long id){
        Business business = businessDao.selectById(id);
        int deleteRows = businessDao.delete(id);
        return deleteRows == 1;
    }

    public boolean modify(Business business){
        return businessDao.update(business) > 0 ? true : false;
    }
}
