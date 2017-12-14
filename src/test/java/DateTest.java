import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/14.
 */
public class DateTest {
    @Test
    public void testDate() {
        String date = "2017年11月28日";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate dateTime = LocalDate.parse(date, dateFormatter);
        System.out.println(dateTime);
    }

    @Test
    public void substring() {
        String str = "原创人生皆是道场";
        System.out.println(str.substring(2));
    }
}
