import com.zhongba01.utils.WebClientUtil;
import org.junit.Test;

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
        String url = "https://www.zhongba01.com/about.html";
//        for (int i = 0; i < 10; i++) {
//            try {
//                WebClientUtil.getDocument(url, 2);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }
}
