package cc.chenzhihao.dictionary.web.response;

/**
 * Des : <dictionary-scaffold> : APIResultCode
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/12 22:59
 */
public enum APIResultCode {

    SUCCESS(200, "SUCCESS"),
    ERROR(500, "SYSTEM_ERROR");

    private Integer code;
    private String message;

    APIResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
