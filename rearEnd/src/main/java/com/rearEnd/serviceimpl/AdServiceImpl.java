package com.rearEnd.serviceimpl;

import com.qiniu.common.QiniuException;
import com.rearEnd.dao.AdDao;
import com.rearEnd.dto.AdDto;
import com.rearEnd.entity.Ad;
import com.rearEnd.service.AdService;
import com.rearEnd.util.FileUtil;
import com.rearEnd.util.QiniuCloudUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    @Override
    // TODO 可以改成获取失败详细原因
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
//        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
//            String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
//            key = fileName;
//            File file = new File(adImageSavePath + fileName);
//            File fileFolder = new File(adImageSavePath);
//            if (!fileFolder.exists()) {
//                fileFolder.mkdirs();
//            }
//            try {
//                adDto.getImgFile().transferTo(file);
//                ad.setImgFileName(fileName);
//                adDao.insert(ad);
//                return true;
//            } catch (IllegalStateException | IOException e) {
//
//                return false;
//            }
//        } else {
//            return false;
//        }
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                byte[] bytes = adDto.getImgFile().getBytes();
                String imageName = UUID.randomUUID().toString();

                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, imageName);
                    ad.setImgFileName(url);
                    System.out.println(url);
                    adDao.insert(ad);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto, condition);
        List<Ad> adList = adDao.selectByPage(condition);
        for (Ad ad : adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            BeanUtils.copyProperties(ad, adDtoTemp);
        }
        return result;
    }

    @Override
    public AdDto getById(Long id) {
        AdDto result = new AdDto();
        Ad ad = adDao.selectById(id);
        BeanUtils.copyProperties(ad, result);
        result.setImg(adImageUrl + ad.getImgFileName());
        return result;
    }

    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                QiniuCloudUtil.delete(ad.getImgFileName());
                byte[] bytes = adDto.getImgFile().getBytes();
                String imageName = UUID.randomUUID().toString();

                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, imageName);
                    ad.setImgFileName(url);
                    System.out.println(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                return false;
            }
        }
        int updateCount = adDao.update(ad);
        if (updateCount != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        Ad ad = adDao.selectById(id);
        try {
            System.out.println(ad.getImgFileName());
            QiniuCloudUtil.delete(ad.getImgFileName());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        int deleteRows = adDao.delete(id);
        return deleteRows == 1;
    }
}
