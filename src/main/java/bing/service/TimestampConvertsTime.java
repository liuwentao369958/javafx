package bing.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConvertsTime {

    //时间戳换时间
    public String timestamp(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s)*1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    //时间转换时间戳
    public  long dateToStamp(String s)  {
        String a="null 00:00:00";
        String b="null 23:59:59";
        if (s.equals(a) ||  s.equals(b)) {
            return 0;
        }else{
            //String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
                long ts = date.getTime() / 1000;
                //res = String.valueOf(ts);
                return ts;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
