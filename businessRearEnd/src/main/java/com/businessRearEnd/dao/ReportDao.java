package com.businessRearEnd.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: Mental
 * @Date:
 */
public interface ReportDao {

    List<Map<String,String>> countOrder(Long id);
}
