package com.frontEnd.serviceimpl;

import com.frontEnd.dao.AdDao;
import com.frontEnd.dto.AdDto;
import com.frontEnd.entity.Ad;
import com.frontEnd.service.AdService;
import com.frontEnd.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    public List<Ad> getAdList(){
        return adDao.getAdList();
    }
}
