
/*
* 测试获得当前时间
* */

import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        //2022-07-09T17:41:12.778+08:00[Asia/Shanghai]
    }
}
