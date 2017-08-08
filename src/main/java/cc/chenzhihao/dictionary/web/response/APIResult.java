package cc.chenzhihao.dictionary.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Des : <dictionary-scaffold> : APIResult
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/12 22:59
 */
@ApiModel
public class APIResult<T> {

    @ApiModelProperty(value = "状态码",notes = "api返回数据的状态吗.建议先判断该状态码在进行数据读取")
    private Integer code;

    @ApiModelProperty(value = "状态信息",notes = "状态提示信息,若遇到非200状态,则可以从该参数获得错误信息")
    private String message;

    @ApiModelProperty(value = "返回信息",notes = "真实返回的对象信息")
    private T result;

    public APIResult() {
    }

    public APIResult(Integer code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setResult(data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String toJson() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        stringBuilder.append("\"code\": \"").append(code).append("\",");
        stringBuilder.append("\"message\": \"").append(message).append("\",");
        stringBuilder.append("\"data\": \"").append(result).append("\"}");

        return stringBuilder.toString();
    }
}
