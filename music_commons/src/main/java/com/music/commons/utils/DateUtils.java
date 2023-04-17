package com.music.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 得到UTC时间，类型是string
     * @return
     */
    public static String timeUTC () {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        // 时间偏移量
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        // 夏令时差
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        // 本地时间扣除这些茶凉，就取得了UTC时间
        calendar.add(Calendar.MILLISECOND,-(zoneOffset+dstOffset));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;
        try{
            dateFormat.parse(UTCTimeBuffer.toString()) ;
            return UTCTimeBuffer.toString() ;
        }catch(ParseException e)
        {
            e.printStackTrace() ;
        }
        return null ;
    }

    public static String time(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'at' z");
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }


}
