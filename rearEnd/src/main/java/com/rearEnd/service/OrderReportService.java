package com.rearEnd.service;

import com.rearEnd.dto.echarts.Option;

public interface OrderReportService {

    /**
     * 按商户类别、按月记录
     * 统计当前系统时间前一天订单数量
     * 并把数据组织成echarts所需参数格式
     *
     * @return 报表参数
     */
    Option count();

    Option money();
}