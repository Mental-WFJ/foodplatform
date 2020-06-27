package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.BusinessDao;
import com.businessRearEnd.dto.BusinessDto;
import com.businessRearEnd.entity.Business;
import com.businessRearEnd.service.BusinessService;
import com.businessRearEnd.util.QiniuCloudUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;


    public Business seletByAppId(Long id){
        return businessDao.seletByAppId(id);
    }

    public boolean updateInfo(BusinessDto businessDto){
        Business business = new Business();
        business.setId(businessDto.getId());
        business.setTitle(businessDto.getTitle());
        business.setSubtitle(businessDto.getSubtitle());
        business.setCity(businessDto.getCity());
        business.setCategory(businessDto.getCategory());
        business.setDesc(businessDto.getDesc());
        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                byte[] bytes = businessDto.getImgFile().getBytes();
                String imageName = UUID.randomUUID().toString();

                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, imageName);
                    business.setImgFileName(url);
                    System.out.println(url);
                    if(businessDao.updateInfo(business) > 0)
                        return true;
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        else{
            business.setImgFileName(null);
            if(businessDao.updateInfo(business) > 0)
                return true;
        }
        return false;
    }
}
