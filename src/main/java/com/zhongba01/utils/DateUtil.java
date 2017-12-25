package com.zhongba01.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */
public final class DateUtil {

    final static long MILLISECONDS_IN_8_HOUR = 8 * 3600 * 1000;

    private DateUtil() {

    }

    /**
     * 解析时间
     *
     * @param string，eg: "document.write(timeConvert('1474348154'))"
     * @return datetime
     */
    public static Date parseTime(String string) {
        final int expectedLength = 3;
        String[] array = string.split("'");
        if (array.length == expectedLength) {
            return new Date(Long.valueOf(array[1]) * 1000 - MILLISECONDS_IN_8_HOUR);
        }
        return null;
    }

    /**
     * 解析日期字符串为LocalDate
     *
     * @param string 2017年11月28日
     * @return LocalDate
     */
    public static Date parseDate(String string) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
            java.util.Date date = sdf.parse(string);
            return new Date(date.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前的UTC时间戳
     *
     * @return 当前的UTC时间戳
     */
    public static Timestamp getUtcNow() {
        return new Timestamp(new java.util.Date().getTime() - MILLISECONDS_IN_8_HOUR);
    }
}
