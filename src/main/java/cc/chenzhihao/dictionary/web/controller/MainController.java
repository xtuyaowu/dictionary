package cc.chenzhihao.dictionary.web.controller;

import cc.chenzhihao.dictionary.domain.Company;
import cc.chenzhihao.dictionary.service.PaService;
import cc.chenzhihao.dictionary.web.request.TestModel;
import cc.chenzhihao.dictionary.web.response.APIListResult;
import cc.chenzhihao.dictionary.web.response.APIResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Des : <dictionary-scaffold> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/3 22:50
 */
@RestController
@RequestMapping(value = "/api/")
@Api(value = "mainController api", description = "哈哈哈")
@CacheConfig(cacheNames = "mainController", keyGenerator = "czhKeyGenerator")
public class MainController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private PaService paService;


    @ApiOperation(
            value = "index",
            response = APIListResult.class,
            httpMethod = "GET",
            nickname = "index,nickname",
            notes = "返回0-9十个数字. 该接口带有缓存支持"
    )
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @Cacheable
    public APIListResult<Integer> index() {
        logger.info("{}", "缓存未命中");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        return super.asSuccess(list, list.size());
    }

    @ApiOperation(
            value = "model",
            response = APIResult.class,
            httpMethod = "POST",
            nickname = "提交一个东西",
            notes = "提交一个东西"
    )
    @RequestMapping(value = "model", method = RequestMethod.POST)
    public APIResult model(
            @ApiParam(name = "testModel", value = "testModel信息,填写姓名和年龄信息")
            @RequestBody TestModel testModel
    ) {
        return super.asSuccess(testModel);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test() {
        paService.saveAllCompanyToDB();
    }
}
