package com.businessRearEnd.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Mental
 * @Date:
 */
public class GetOrder {

    /**
     * 获得唯一订单号
     */
    public static String getUniqueOrder() {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        String format2 = format.format(new Date());
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            // 有可能是负数
            hashCodeV = -hashCodeV;
        }
        return "pk" + format2 + String.format("%012d", hashCodeV);
    }
}
