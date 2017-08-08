package cc.chenzhihao.dictionary.domain.enums;

/**
 * Des : <dictionary> : 时间转件类型枚举
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/31 22:46
 */
public enum DateFormat {

    Date("yyyy-MM-dd"), DateTime("yyyy-MM-dd HH:mm:ss"), DatePath("yyyy/MM/dd"), DatePathSingle("yyyy/M/d");

    private final String format;

    private DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

}
