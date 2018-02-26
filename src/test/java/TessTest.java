import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * 中巴价值投资研习社
 *
 * @ author: tang jian hua
 * @ date: 2018/02/26.
 */
public class TessTest {
    @Test
    public void classify1() {
        //设置APPID/AK/SK
        final String APP_ID = "10853562";
        final String API_KEY = "cz01iVEbRKQOyp7u5vDdw7I8";
        final String SECRET_KEY = "qD65AfzyKzgi36GYoPYUQ5YrzXpfUWsL";

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
//        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        String path = "D:\\tess\\dd.png";
        JSONObject res = client.basicGeneral(path, options);
        System.out.println(res.toString(2));
    }
}
