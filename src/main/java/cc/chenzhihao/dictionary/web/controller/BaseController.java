package cc.chenzhihao.dictionary.web.controller;

import cc.chenzhihao.dictionary.domain.vo.PageInfoVO;
import cc.chenzhihao.dictionary.web.response.APIListResult;
import cc.chenzhihao.dictionary.web.response.APIResult;
import cc.chenzhihao.dictionary.web.response.APIResultCode;
import cc.chenzhihao.dictionary.web.response.ListPackage;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Des : <dictionary-scaffold> : BaseController 提供控制器最基本的功能
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/12 22:59
 */
@Component
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected <T> APIResult<T> asSuccess(T t) {
        logger.info("BaseController.asSuccess(T).params({})", t);
        APIResult<T> apiResult = new APIResult<>(APIResultCode.SUCCESS.getCode(), APIResultCode.SUCCESS.getMessage(), t);
        logger.info("BaseController.asSuccess(T).result: {}", apiResult.toJson());
        return apiResult;
    }

    protected APIResult<String> asError(String message) {
        return new APIResult<>(APIResultCode.ERROR.getCode(), message, null);
    }

    protected APIResult<String> asError(Integer code, String message) {
        logger.error("BaseController.asError(code,message).params({},{})", code, message);
        APIResult<String> apiResult = new APIResult<>(code, message, null);
        logger.error("BaseController.asError(code,message).result: {}", apiResult.toJson());
        return apiResult;
    }

    protected <T> APIListResult<T> asSuccess(List<T> t, int rows) {
        logger.info("BaseController.asSuccess(t,row).params({},{})", t, rows);
        ListPackage<T> list = new ListPackage<>();
        list.setList(t);
        list.setRows(rows);
        APIListResult<T> apiResult = new APIListResult<>(APIResultCode.SUCCESS.getCode(), APIResultCode.SUCCESS.getMessage(), list);
        logger.info("BaseController.asSuccess(t,row).result: {}", apiResult.toJson());
        return apiResult;
    }

    protected String getRequestIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected <T> PageInfoVO<T> asPageInfoVO(PageInfo pageInfo, List<T> dataList) {
        PageInfoVO<T> pageInfoVO = new PageInfoVO<>();
        BeanUtils.copyProperties(pageInfo, pageInfoVO);
        pageInfoVO.setDataList(dataList);
        pageInfoVO.setFirstPage(pageInfo.isIsFirstPage());
        pageInfoVO.setLastPage(pageInfo.isIsLastPage());
        logger.info("BaseController.asPageInfoVO(pageInfo,dataList).result: {}", pageInfoVO);
        return pageInfoVO;
    }
}
