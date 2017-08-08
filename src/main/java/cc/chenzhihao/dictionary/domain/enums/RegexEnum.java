package cc.chenzhihao.dictionary.domain.enums;


import java.util.regex.Pattern;

/**
 * Des : <promotion> : 常用正则表达式校验工具
 *
 * @author chenzhihao
 * @version 创建时间: 2017/2/9 13:28
 */
public enum RegexEnum {
    REGEX("", ""),
    EMAIL("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?", "邮箱校验"),
    TELPHONE("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", "手机号校验"),
    PHONE("\\d{3}-\\d{8}|\\d{4}-\\d{7}", "电话号码校验(“XXX-XXXXXXX”、”XXXX-XXXXXXXX”、”XXX-XXXXXXX”、”XXX-XXXXXXXX”、”XXXXXXX”和”XXXXXXXX)");
    private String regex;
    private String des;

    RegexEnum(String regex, String des) {
        this.regex = regex;
        this.des = des;
    }

    public boolean matches(String data) {
        return matches(this.regex, data);
    }

    public static boolean matches(String regex, String data) {
        return Pattern.matches(regex, data);
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
