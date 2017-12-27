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
    public void testAuthor(){
        String str  = "转自：(文 | ：:老?杨)";
        str = str.replaceAll("[\\(\\):：?]", "");
        str = str.replaceAll("[文/|文 \\||作者|转自|转载]", "");
        System.out.println(str);
    }
}
