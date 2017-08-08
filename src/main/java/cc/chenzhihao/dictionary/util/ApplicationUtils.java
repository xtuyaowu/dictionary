package cc.chenzhihao.dictionary.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Des : <promotion> : 应用工具类
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/31 22:35
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生一个8位MixId
     *
     * @return MixId
     */
    public static String randomMIXID() {
        return randomUUID().substring(0, 8);
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    /**
     * 动态加密
     *
     * @param src
     * @return
     */
    public static String encode(String src) {
        StringBuilder sb = new StringBuilder();
        double ran = new Random().nextDouble();
        int nn1 = (int) (7F * ran + 1);
        sb.append(nn1).append("_");
        nn1 = nn1 + 7;
        for (int i = 0; i < src.length(); i++) {
            char nc = src.substring(i, i + 1).toCharArray()[0];
            int intTmp = nc ^ nn1;
            if ((intTmp >= 35 && intTmp <= 47 && intTmp != 39) || (intTmp >= 64 && intTmp <= 90) || (intTmp >= 97 && intTmp <= 122)) {
                sb.append((char) intTmp).append("_");
            } else {
                sb.append(intTmp).append("_");
            }
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    /**
     * 动态解密
     *
     * @param src
     * @return
     */
    public static String decode(String src) throws NumberFormatException {
        StringBuilder sb = new StringBuilder();
        String arr[] = src.split("_");
        long nn = Long.valueOf(arr[0]) + 7;
        for (int i = 1; i < arr.length; i++) {
            if (isNumberic(arr[i])) {
                long nc = (Integer.parseInt(Long.valueOf(arr[i]).toString()) ^ nn);
                sb.append((char) nc);
            } else {
                long nc = (Integer.parseInt(Long.valueOf(arr[i].toCharArray()[0]).toString()) ^ nn);
                sb.append((char) nc);
            }
        }
        return sb.toString();
    }

    public static boolean isNumberic(String s) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(s).matches();
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = genCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    private static Map<String, Cookie> genCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }


}
