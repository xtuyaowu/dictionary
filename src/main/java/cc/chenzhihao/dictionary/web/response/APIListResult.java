package cc.chenzhihao.dictionary.web.response;

/**
 * Des : <dictionary-scaffold> : APIListResult
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/12 22:59
 */
public class APIListResult<T> extends APIResult<ListPackage<T>> {

    public APIListResult() {
    }

    public APIListResult(Integer code, String message, ListPackage<T> data) {
        super(code, message, data);
    }

}
