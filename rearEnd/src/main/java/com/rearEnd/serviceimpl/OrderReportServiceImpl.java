package com.rearEnd.serviceimpl;

import com.rearEnd.dao.ReportDao;
import com.rearEnd.dto.echarts.Option;
import com.rearEnd.dto.echarts.Serie;
import com.rearEnd.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderReportServiceImpl implements OrderReportService {

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public Option count() {
		Option option = new Option();
		List<Map<String, String>> list = reportDao.countOrder();
		// 类别
		Set<String> categoryNameSet = new TreeSet<>();
		// 类别+时间为KEY，数量为VALUE
		Map<String,Long> countMap = new HashMap<String,Long>();
		for(Map<String, String> map : list) {
			categoryNameSet.add(map.get("type"));
			Object object = map.get("type");
			Object object2 = map.get("month");
			Object object3 = map.get("count");
			System.out.println(object + " " + object2 + " " + object3);
			countMap.put(String.valueOf(object) + String.valueOf(object2), Long.valueOf(String.valueOf(object3)));
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

	@Override
	public Option money() {
		Option option = new Option();
		List<Map<String, String>> list = reportDao.moneyOrder();
		// 类别
		Set<String> categoryNameSet = new TreeSet<>();
		// 类别+时间为KEY，数量为VALUE
		Map<String,Long> countMap = new HashMap<String,Long>();
		for(Map<String, String> map : list) {
			categoryNameSet.add(map.get("type"));
			Object object = map.get("type");
			Object object2 = map.get("month");
			Object object3 = map.get("total");
			System.out.println(object + " " + object2 + " " + object3);
			double d = Double.valueOf(String.valueOf(object3));
			countMap.put(String.valueOf(object) + String.valueOf(object2), new Double(d).longValue());
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