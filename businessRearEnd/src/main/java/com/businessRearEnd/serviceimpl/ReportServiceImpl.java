package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.ReportDao;
import com.businessRearEnd.dto.echarts.Option;
import com.businessRearEnd.dto.echarts.Serie;
import com.businessRearEnd.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportDao reportDao;

    @Override
    public Option countOrder(Long id) {
        Option option = new Option();
        List<Map<String, String>> list = reportDao.countOrder(id);
        // 类别
        Set<String> categoryNameSet = new TreeSet<>();
        categoryNameSet.add("销量");
        categoryNameSet.add("营业额");
        // 类别+时间为KEY，数量为VALUE
        Map<String,Long> countMap = new HashMap<String,Long>();
        for(Map<String, String> map : list) {
            Object object = map.get("month");
            Object object2 = map.get("total");
            Object object3 = map.get("count");
            System.out.println(object + " " + object2 + " " + object3);
            double d = Double.valueOf(String.valueOf(object2));
            countMap.put("营业额" + String.valueOf(object), new Double(d).longValue());
            countMap.put("销量" + String.valueOf(object), Long.valueOf(String.valueOf(object3)));
        }
        // 设置参数中线条的分类
        option.getLegend().setData( new ArrayList<>(categoryNameSet));
        // 设置参数的X轴坐标
        List<String> months = new ArrayList<String>();
        for(int i = 1; i <= 12; i++) {
            months.add(String.format("%d", i));
        }
        option.getxAxis().setData(months);

        for(Map.Entry<String, Long> entry : countMap.entrySet())
            System.out.println("key=" + entry.getKey() + " value:" + entry.getValue());

        // 设置线条的名称和数值
        for(String categoryName : option.getLegend().getData()) {
            Serie serie = new Serie();
            option.getSeries().add(serie);
            serie.setName(categoryName);
            serie.setType("line");
            for(String month : months) {
                serie.getData().add(countMap.get(categoryName + month) == null ? 0 : countMap.get(categoryName + month));
            }
        }

        return option;
    }
}
