package com.rearEnd.task;

import com.rearEnd.constant.SysParamKeyConst;
import com.rearEnd.dao.BusinessDao;
import com.rearEnd.dao.SysParamDao;
import com.rearEnd.entity.SysParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商户相关的定时任务
 */
@Component("BusinessTask")
public class BusinessTask {

    private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);

    @Resource
    private BusinessDao businessDao;

    @Resource
    private SysParamDao sysParamDao;

    /**
     * 同步
     */
    @Transactional
    public void synStar() {
        logger.info("synStar start");
        // 先获取上次同步的时间(最后同步时间)
        SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        System.out.println(sysParam.getParamValue());
        Map<String, Date> map = new HashMap<>();
        map.put("startTime", sysParam.getParamValue());
        // 以当前系统时间做为同步的截止时间，同时也做为下次同步的起始时间
        Date endTime = new Date();
        map.put("endTime", endTime);
        // 如果起始时间为NULL，那说明是第一次同步，需要将历史数据全步同步，一直同步到当前系统时间为止。
        int starCount = businessDao.updateStar(map);
        int numCount = businessDao.updateNumber(map);
        int priceCount = businessDao.updatePrice(map);
        System.out.println(starCount + " " + numCount + " " + priceCount);
        // 将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
        SysParam sysParamForUpdate = new SysParam();
        sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        sysParamForUpdate.setParamValue(endTime);
        sysParamDao.updateByKey(sysParamForUpdate);
        logger.info("synStar end");
    }
}