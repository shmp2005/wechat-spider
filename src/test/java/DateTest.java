import com.gargoylesoftware.htmlunit.WebClient;
import com.zhongba01.utils.WebClientUtil;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/14.
 */
public class DateTest {
    @Test
    public void testRandomUtil() {

        System.out.println(System.currentTimeMillis());
        long dd = 1474348154;
        System.out.println(new Date(dd*1000));
    }

    @Test
    public void testDate() {
        String date = "2017年11月28日";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate dateTime = LocalDate.parse(date, dateFormatter);
        System.out.println(dateTime);
    }

    @Test
    public void substring() {
        String str = null;
        StringBuilder sb = new StringBuilder(str + "");
        sb.append("atang");
        System.out.println(sb.toString());
    }

    @Test
    public void encode(){
        String string = "FUDANMBA-AMCCLUB";
        System.out.println(WebClientUtil.toUtf8(string));
    }
}
