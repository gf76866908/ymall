package until;

import com.ymall.util.MD5Util;

/**
 * Created by zc on 2017/6/14.
 */
public class test {
    public static void main(String[] args) {
        String str = MD5Util.MD5EncodeUtf8("1234567");
        System.out.println(str);
    }
}
