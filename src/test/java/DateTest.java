import com.zhongba01.utils.DateUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/14.
 */
public class DateTest {
    @Test
    public void testAuthor() {
        String str = "转自：(文 | ：:老?杨@)";
        str = str.replaceAll("[\\(\\):：?@]", "");
        str = str.replaceAll("[文/|文 \\||作者|转自|转载]", "");
        System.out.println(str);
    }

    @Test
    public void testWebClient() {
        String text = "document.write(timeConvert('1514962942'))";
        Timestamp ts = DateUtil.parseTimestamp(text);
        System.out.println(ts);
    }

    @Test
    public void testSplit() {
        String url = "tlinfo:essay,essay_1234,sweb_url?inQuanApp=1";
        String[] array = url.split("[:,]");
        System.out.println(array);
    }

    @Test
    public void testEncoding() {
        System.out.println("汤建华" + LocalDateTime.now());
    }
}
