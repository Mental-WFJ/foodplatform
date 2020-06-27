package com.rearEnd.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {

	/**
	 * 按商户类别和月份统计订单数
	 * @return 订单数统计结果集，key值说明：
	 *                type 商户类别的中文名
	 *                month 月份
	 *                count 订单数量
	 */
	List<Map<String,String>> countOrder();

	/**
	 * 按商户类别和月份统计营业额
	 * @return 订单数统计结果集，key值说明：
	 *                type 商户类别的中文名
	 *                month 月份
	 *                total 营业额
	 */
	List<Map<String,String>> moneyOrder();
}