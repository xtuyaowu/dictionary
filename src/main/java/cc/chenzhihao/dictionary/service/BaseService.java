package cc.chenzhihao.dictionary.service;

import cc.chenzhihao.dictionary.domain.Company;

import java.io.IOException;
import java.util.List;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/4 22:21
 */
public interface BaseService {

    /**
     * 做初始化动作
     */
    void init();

    /**
     * 爬取url列表
     *
     * @return url列表
     */
    List<String> getList() throws IOException, InterruptedException;

    /**
     * 将列表存入队列
     *
     * @param urls 列表
     * @return 操作个数
     */
    void pushListToRedis(List<String> urls);

    /**
     * 现实缓存中的列表url
     *
     * @return url列表
     */
    List<String> showList();

    List<String> showCompanys();

    /**
     * 从url中获取公司信息
     */
    Company getCompany(String url);

    /**
     * 将公司信息存起来
     *
     * @param company 公司信息对象
     */
    void save(Company company);

    /**
     * 清空url队列
     */
    void trimList();

    /**
     * 清空公司信息列表
     */
    void trimCompanysList();

    /**
     * 返回url队列长度
     *
     * @return 队列长度
     */
    Long listSize();

    /**
     * 返回公司队列长度
     *
     * @return 长度
     */
    Long companySize();

    String popUrl();

    Company popCompany();

    /**
     * 将缓存中的公司队列中的数据转储到数据库中
     */
    void saveAllCompanyToDB();

    Company saveTest(Company  c);
}
