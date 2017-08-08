package cc.chenzhihao.dictionary.service.impl;

import cc.chenzhihao.dictionary.dao.CompanyRedisDao;
import cc.chenzhihao.dictionary.dao.ListRedisDao;
import cc.chenzhihao.dictionary.dao.repository.CompanyDao;
import cc.chenzhihao.dictionary.domain.Company;
import cc.chenzhihao.dictionary.service.PaService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Des : <dictionary> : 猎聘爬取实现
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/5 11:32
 */
@Service("paService")
public class PaServiceImpl implements PaService {

    private final Logger logger = LoggerFactory.getLogger(PaServiceImpl.class);

    //基础url
    private final String baseUrl;

    //启始页号
    private int startPage;

    //结束页号
    private int endPage;

    //列表缓存队列的key
    private final String redisListQueueKey;

    //缓存中公司数据列表的key
    private final String redisCompanyListKey;

    //Cookie
    public Map<String, String> cookies;

    @Autowired
    private ListRedisDao listRedisDao;

    @Autowired
    private CompanyRedisDao companyRedisDao;
    @Autowired
    private CompanyDao companyDao;


    public PaServiceImpl() {
        this.baseUrl = "http://www.chinahr.com/company/";
        this.startPage = 1;
        this.endPage = 100;
        this.redisCompanyListKey = "companyList";
        this.redisListQueueKey = "companys";
        cookies = new HashMap<>();
//        init();
    }

    public PaServiceImpl(String baseUrl, int startPage, int endPage, String redisListQueueKey, String redisCompanyListKey) {
        this.baseUrl = baseUrl;
        this.startPage = startPage;
        this.endPage = endPage;
        this.redisListQueueKey = redisListQueueKey;
        this.redisCompanyListKey = redisCompanyListKey;
        init();
    }

    @Override
    public void init() {
        CloseableHttpClient client = null;
        CookieStore cookieStore = new BasicCookieStore();
        try {
            client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpGet get = new HttpGet(baseUrl);
            client.execute(get);
            List<Cookie> cookies = cookieStore.getCookies();
            this.cookies = new HashMap<>();
            for (Cookie cookie :
                    cookies) {
                this.cookies.put(cookie.getName(), cookie.getValue());
            }
            logger.info("Call url: {}", this.baseUrl);
            logger.info("Cookie : {}", this.cookies);
        } catch (IOException e) {
            logger.error("init method throw IOException : {}", e.getMessage());
        }
        logger.info("初始化成功");
    }


    private String zhyc(String url) throws IOException {
        Document companyPage = Jsoup.connect(url)
                .header("Cookie", "uid=56bfa65763cf945d428111a6d36d1c3a; chrId=b2710c5a7525490882d1bd2a77e0c431; gr_user_id=916d9988-dc2c-496c-af6f-c063e19dc687; wmda_uuid=5d9d8806cd48f9703cfe8379f0e0eeac; wmda_new_uuid=1; wmda_visited_projects=%3B1732047435009; RecentVisitCity=138_dandong; RecentVisitCityFullpathPc=\"13,138\"; 58tj_uuid=d289f733-d487-4158-969f-055d1e70a4dc; channel=social; new_session=0; new_uv=1; utm_source=; spm=; init_refer=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253D6x3JrIqtHP9QeBPxp4qgoVB3hKmtiajVdszwpF1JGwQqVL6A3YtE4JT4xy6HJzgS%2526wd%253D%2526eqid%253Ddc0edbfa00002c1c0000000259891bb3")
                .header("Host", "www.chinahr.com").get();
        Elements nextButton = companyPage.select("body > div.main > div.hotJob-city.bor > div.pager > a:last-child");
        String nextUrl = nextButton.attr("href");
        if (nextUrl.equals(url)) {
            return null;
        } else {
            return nextUrl;
        }
    }

    @Override
    public List<String> getList() throws IOException, InterruptedException {

        String baseurl = "http://www.chinahr.com";
        String url = "http://www.chinahr.com/company/";

//        Document companyPage = Jsoup.connect(url)
//                .header("Cookie", "uid=56bfa65763cf945d428111a6d36d1c3a; chrId=b2710c5a7525490882d1bd2a77e0c431; gr_user_id=916d9988-dc2c-496c-af6f-c063e19dc687; wmda_uuid=5d9d8806cd48f9703cfe8379f0e0eeac; wmda_new_uuid=1; wmda_visited_projects=%3B1732047435009; RecentVisitCity=138_dandong; RecentVisitCityFullpathPc=\"13,138\"; 58tj_uuid=d289f733-d487-4158-969f-055d1e70a4dc; channel=social; new_session=0; new_uv=1; utm_source=; spm=; init_refer=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253D6x3JrIqtHP9QeBPxp4qgoVB3hKmtiajVdszwpF1JGwQqVL6A3YtE4JT4xy6HJzgS%2526wd%253D%2526eqid%253Ddc0edbfa00002c1c0000000259891bb3").get();
//        Elements hydoc = companyPage.select("body > div.main > div.cityList.bor > ul a");
//
//        for (Element e : hydoc) {
//            if (e.text().equals("收起>>") || e.text().equals("更多>>"))
//                continue;
//            String hyurl = baseurl + e.attr("href");
//            listRedisDao.save("zhyc_cityList", hyurl);
//        }
        logger.info(listRedisDao.show("zhyc_cityList").toString());


        while (listRedisDao.size("zhyc_cityList") > 0) {
            int i = 1;
            String currentUrl = listRedisDao.pop("zhyc_cityList");
            logger.info("城市：" + currentUrl);
            while (true) {
                logger.info("当前页面：" + currentUrl);

                Document companyListPage = Jsoup.connect(currentUrl)
                        .header("Cookie", "uid=56bfa65763cf945d428111a6d36d1c3a; chrId=b2710c5a7525490882d1bd2a77e0c431; gr_user_id=916d9988-dc2c-496c-af6f-c063e19dc687; wmda_uuid=5d9d8806cd48f9703cfe8379f0e0eeac; wmda_new_uuid=1; wmda_visited_projects=%3B1732047435009; RecentVisitCity=138_dandong; RecentVisitCityFullpathPc=\"13,138\"; 58tj_uuid=d289f733-d487-4158-969f-055d1e70a4dc; channel=social; new_session=0; new_uv=1; utm_source=; spm=; init_refer=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253D6x3JrIqtHP9QeBPxp4qgoVB3hKmtiajVdszwpF1JGwQqVL6A3YtE4JT4xy6HJzgS%2526wd%253D%2526eqid%253Ddc0edbfa00002c1c0000000259891bb3").get();
                Elements companyPageUrls = companyListPage.select("body > div.main > div.hotJob-city.bor > div.hotJobList-city > ul > li > a:nth-child(2)");
                for (Element e :
                        companyPageUrls) {
                    listRedisDao.save("zhyc_companyList", baseurl + e.attr("href"));
                    logger.info("存储成功，公司：" + e.text() + " :: " + baseurl + e.attr("href"));
                }

                currentUrl = zhyc(currentUrl);
                if (currentUrl == null)
                    break;
                logger.info("下一页：" + currentUrl);
                i++;
            }
            logger.info("该城市的公司列表爬取完了，休息5秒");
            Thread.sleep(5000);
        }

//        String czhyurl;
//        Document hypage;
//        Elements companys;
//        List<String> companyUrls = new ArrayList<>();
//        while (!hyurls.isEmpty()) {
//            czhyurl = hyurls.pop();
//            hypage = Jsoup.connect(czhyurl).header("Cookie", "chrId=8e3f66adc1d9486abedf439ee1ba291a; wmda_uuid=85646e5cdf19164aac3823285cee6a4f; wmda_new_uuid=1; wmda_visited_projects=%3B1732047435009; gr_user_id=cf61c966-2aa4-4709-9f68-3806e05ec231; wmda_session_id=1501687139539-5625d588-c0bb-7f2f; gr_session_id_be17cdb1115be298=d9a1b41e-0d0f-481d-b3c7-6583c4c142cc; RecentVisitCity=134_dalian; RecentVisitCityFullpathPc=\"13,134\"; 58tj_uuid=f0455ce7-ac6c-4423-b714-801a765e40fd; channel=social; new_session=0; new_uv=2; utm_source=; spm=; init_refer=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DSubVsfBjyiNXviSwYe8tyKgoeJDXMofM1JhIpoZu-AWwb1-GTSFWVAhB0NShTYdi%2526wd%253D%2526eqid%253Dfa31c08400021e13000000035981e2ec")
//                    .header("Host", "www.chinahr.com")
//                    .header("Referer", "http://www.chinahr.com/company/").get();
//            companys = hypage.select("body > div.main > div.hotJob-city.bor > div.hotJobList-city > ul > li > a:nth-child(2)");
//            for (Element e : companys) {
//                companyUrls.add(baseurl + e.attr("href"));
//            }
//            companys = hypage.select("body");
//            System.out.println("爬取总数:" + companyUrls.size());
//        }

        return null;
    }

//    @Override
//    public List<String> getList() throws IOException, InterruptedException {
//        Document cityDoc = null;
//        Elements citys = null;
//        Stack<String> cityUrls = new Stack<>();
//        List<String> companyUrlList = new ArrayList<>();
//        try {
//            cityDoc = Jsoup.connect(baseUrl)
//                    .header("Host", "company.zhaopin.com")
//                    .header("Cookie", "_jzqckmp=1; JSSearchModel=0; BLACKSTRIP=yes; LastCity%5Fid=530; LastCity=%e5%8c%97%e4%ba%ac; dywez=95841923.1501726871.2.2.dywecsr=zhaopin.com|dyweccn=(referral)|dywecmd=referral|dywectr=undefined|dywecct=/; pcc=r=338875362&t=1; _jzqx=1.1501688341.1501726872.1.jzqsr=zhaopin%2Ecom|jzqct=/.-; LastSearchHistory=%7b%22Id%22%3a%229870601d-d478-430a-8219-e8a268b5cb2d%22%2c%22Name%22%3a%22java+%2b+%e5%8c%97%e4%ba%ac%22%2c%22SearchUrl%22%3a%22http%3a%2f%2fsou.zhaopin.com%2fjobs%2fsearchresult.ashx%3fjl%3d%25e5%258c%2597%25e4%25ba%25ac%26kw%3djava%26sm%3d0%26p%3d1%22%2c%22SaveTime%22%3a%22%5c%2fDate(1501726886691%2b0800)%5c%2f%22%7d; Hm_lvt_38ba284938d5eddca645bb5e02a02006=1501688341,1501726871; Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1501726896; stayTimeCookie=1501726895668; referrerUrl=http%3A//company.zhaopin.com/CC476447134.htm; dywea=95841923.4049817082766316000.1501688341.1501688341.1501726871.2; dywec=95841923; dyweb=95841923.7.9.1501726886352; urlfrom=121126445; urlfrom2=121126445; adfcid=none; adfcid2=none; adfbid=0; adfbid2=0; __utma=269921210.1836954817.1501688341.1501688341.1501726871.2; __utmb=269921210.6.9.1501726886355; __utmc=269921210; __utmz=269921210.1501726871.2.2.utmcsr=zhaopin.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _jzqa=1.4525575824012991000.1501688341.1501688341.1501726872.2; _jzqc=1; _jzqb=1.4.10.1501726872.1; _qzja=1.721441300.1501688389181.1501688389182.1501726898385.1501688572708.1501726898385.0.0.0.3.2; _qzjb=1.1501726898385.1.0.0.0; _qzjc=1; _qzjto=1.1.0").get();
//            citys = cityDoc.select(".city_nav > div > ul > li > strong > a");
//            for (Element city :
//                    citys) {
//                cityUrls.push("http:" + city.attr("href"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        while (!cityUrls.isEmpty()) {
//
//            String webclassesUrl = cityUrls.pop();
//            int i = 1;
//            while (true) {
//                String url = webclassesUrl + "p" + i++;
//                Connection connection = Jsoup.connect(url)
//                        .header("Host", "company.zhaopin.com")
//                        .header("Cookie", "_jzqckmp=1; JSSearchModel=0; BLACKSTRIP=yes; LastCity%5Fid=530; LastCity=%e5%8c%97%e4%ba%ac; dywez=95841923.1501726871.2.2.dywecsr=zhaopin.com|dyweccn=(referral)|dywecmd=referral|dywectr=undefined|dywecct=/; pcc=r=338875362&t=1; _jzqx=1.1501688341.1501726872.1.jzqsr=zhaopin%2Ecom|jzqct=/.-; LastSearchHistory=%7b%22Id%22%3a%229870601d-d478-430a-8219-e8a268b5cb2d%22%2c%22Name%22%3a%22java+%2b+%e5%8c%97%e4%ba%ac%22%2c%22SearchUrl%22%3a%22http%3a%2f%2fsou.zhaopin.com%2fjobs%2fsearchresult.ashx%3fjl%3d%25e5%258c%2597%25e4%25ba%25ac%26kw%3djava%26sm%3d0%26p%3d1%22%2c%22SaveTime%22%3a%22%5c%2fDate(1501726886691%2b0800)%5c%2f%22%7d; Hm_lvt_38ba284938d5eddca645bb5e02a02006=1501688341,1501726871; Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1501726896; stayTimeCookie=1501726895668; referrerUrl=http%3A//company.zhaopin.com/CC476447134.htm; dywea=95841923.4049817082766316000.1501688341.1501688341.1501726871.2; dywec=95841923; dyweb=95841923.7.9.1501726886352; urlfrom=121126445; urlfrom2=121126445; adfcid=none; adfcid2=none; adfbid=0; adfbid2=0; __utma=269921210.1836954817.1501688341.1501688341.1501726871.2; __utmb=269921210.6.9.1501726886355; __utmc=269921210; __utmz=269921210.1501726871.2.2.utmcsr=zhaopin.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _jzqa=1.4525575824012991000.1501688341.1501688341.1501726872.2; _jzqc=1; _jzqb=1.4.10.1501726872.1; _qzja=1.721441300.1501688389181.1501688389182.1501726898385.1501688572708.1501726898385.0.0.0.3.2; _qzjb=1.1501726898385.1.0.0.0; _qzjc=1; _qzjto=1.1.0");
//                try {
//                    connection.execute().statusCode();
//                } catch (HttpStatusException e) {
//                    break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Document companyListDoc = connection.get();
//                System.out.println("========================================================当前页面： " + url);
//                Elements companyLinkElements = companyListDoc.select(".checkjobs a");
//                for (Element companyLinkElement :
//                        companyLinkElements) {
//                    System.out.println(companyLinkElement.text() + ":" + companyLinkElement.attr("href"));
//                    companyUrlList.add(companyLinkElement.attr("href"));
//                }
//            }
//
//            System.out.println("+++++++++++=====暂停爬取，开始写入。共" + companyUrlList.size() + "条====++++++++++++");
//            pushListToRedis(companyUrlList);
//            companyUrlList = new ArrayList<>();
//            System.out.println("写入成功！");
//            System.out.println("休息一会（5秒）");
//            Thread.sleep(5000);
//        }
//
//        return cityUrls;
//    }

//    @Override
//    public List<String> getList() {
//        int page = this.startPage;
//        Document listDoc;
//        Elements listItems;
//        List<String> companyUrls = new ArrayList<>();
//        while (page <= endPage) {
//            String currentUrl = baseUrl + "pn" + page;
//            logger.info("当前爬取第{}页，URL: {}", page, currentUrl);
//            try {
//                listDoc = Jsoup.connect(currentUrl)
//                        .header("Cookie", "_uuid=B1F9F40BFFA8480B11F0761C49FCB8CB; _fecdn_=1; gr_user_id=5ba1a1a5-e252-4700-9c2d-f36efcbc0190; __uuid=1501929535692.38; JSESSIONID=FA1BEA050F0B8C0A870DD04BC62FDB43; verifycode=c25bcd44f18a49238f8cf13b8e355304; Hm_lvt_a2647413544f5a04f00da7eee0d5e200=1501929536; Hm_lpvt_a2647413544f5a04f00da7eee0d5e200=1501931161; cd3af4b9=68256487850faedacc83acf05a8ed094; 47219885=8cd086711b01ca924b95499832e9b962; lt_auth=ub0MPCYEzlv5t3CNjzFd4PpEiomvUz2f9Xtfhh5RgtboCvzh4P3rQg2Hp7kExBMhlEx1JMULNLD%2F%0D%0APOv%2FyHpD7UQSwGmklICxtPuk2GECSd1nc%2Fui0fSokJ%2FXS893kCtWw3Mz9SpIwU%2F0uhR0MdG4z2Ph%0D%0Ajoju1bSl%2B%2Fs%3D%0D%0A; UniqueKey=2ff2d453258fdb1d1a667e00ad0d9493; user_kind=0; is_lp_user=true; login_temp=islogin; em_username=4447001273j1723972233; em_token=YWMtwdqlhnX2EeetBi_WNgw5PU8TxtBqMxHkgus5YKfC9XHB14DgdfYR54wbAVi_n4eCAwMAAAFdmOaQAgBPGgBqINPNoFFA7zSGKUP3ZCUdOUVryzTQmM3Lm_qJmy463A; abtest=0; gr_session_id_bad1b2d9162fab1f80dde1897f7a2972=18fb8101-d094-4399-ac6e-3f06c285f06d; gr_cs1_18fb8101-d094-4399-ac6e-3f06c285f06d=UniqueKey%3A2ff2d453258fdb1d1a667e00ad0d9493; __tlog=1501929534331.61%7C00000000%7C00000000%7C00000000%7C00000000; __session_seq=6; __uv_seq=6; _mscid=00000000; user_vip=0; user_name=%E9%99%88%E5%BF%97%E6%98%8A; user_photo=55557f3b28ee44a8919620ce01a.gif")
//                        .get();
//                listItems = listDoc.select(".company-list .list-item .item-top > a");
//                if (listItems.isEmpty()) {
//                    logger.error("Page info : {}", listDoc.body());
//                    break;
//                }
//                for (Element e :
//                        listItems) {
//                    companyUrls.add(e.attr("href"));
//                }
//            } catch (IOException e) {
//                logger.error("getList method throw IOException : {}", e.getMessage());
//                e.printStackTrace();
//                break;
//            }
//            page++;
//        }
//
//        return companyUrls;
//    }


    @Override
    public void pushListToRedis(List<String> urls) {
        if (!urls.isEmpty())
            listRedisDao.save(urls, this.redisListQueueKey);
        logger.info("列表入队成功");
    }

    @Override
    public List<String> showList() {
        return listRedisDao.show(redisListQueueKey);
    }

    @Override
    public List<String> showCompanys() {
        return companyRedisDao.show(redisCompanyListKey);
    }

    @Override
    public Company getCompany(String url) {
        Company company = new Company();
        Document companyDoc = null;
        Element body = null;
        try {
            companyDoc = Jsoup.connect(url)
                    .header("Cookie", "_uuid=B1F9F40BFFA8480B11F0761C49FCB8CB; _fecdn_=1; gr_user_id=5ba1a1a5-e252-4700-9c2d-f36efcbc0190; __uuid=1501929535692.38; JSESSIONID=FA1BEA050F0B8C0A870DD04BC62FDB43; verifycode=c25bcd44f18a49238f8cf13b8e355304; Hm_lvt_a2647413544f5a04f00da7eee0d5e200=1501929536; Hm_lpvt_a2647413544f5a04f00da7eee0d5e200=1501931161; cd3af4b9=68256487850faedacc83acf05a8ed094; 47219885=8cd086711b01ca924b95499832e9b962; lt_auth=ub0MPCYEzlv5t3CNjzFd4PpEiomvUz2f9Xtfhh5RgtboCvzh4P3rQg2Hp7kExBMhlEx1JMULNLD%2F%0D%0APOv%2FyHpD7UQSwGmklICxtPuk2GECSd1nc%2Fui0fSokJ%2FXS893kCtWw3Mz9SpIwU%2F0uhR0MdG4z2Ph%0D%0Ajoju1bSl%2B%2Fs%3D%0D%0A; UniqueKey=2ff2d453258fdb1d1a667e00ad0d9493; user_kind=0; is_lp_user=true; login_temp=islogin; em_username=4447001273j1723972233; em_token=YWMtwdqlhnX2EeetBi_WNgw5PU8TxtBqMxHkgus5YKfC9XHB14DgdfYR54wbAVi_n4eCAwMAAAFdmOaQAgBPGgBqINPNoFFA7zSGKUP3ZCUdOUVryzTQmM3Lm_qJmy463A; abtest=0; gr_session_id_bad1b2d9162fab1f80dde1897f7a2972=18fb8101-d094-4399-ac6e-3f06c285f06d; gr_cs1_18fb8101-d094-4399-ac6e-3f06c285f06d=UniqueKey%3A2ff2d453258fdb1d1a667e00ad0d9493; __tlog=1501929534331.61%7C00000000%7C00000000%7C00000000%7C00000000; __session_seq=6; __uv_seq=6; _mscid=00000000; user_vip=0; user_name=%E9%99%88%E5%BF%97%E6%98%8A; user_photo=55557f3b28ee44a8919620ce01a.gif")
                    .get();
            body = companyDoc.body();

            String name = body.select("body > div.main > div.mainLeft > div:nth-child(1) > h1").text();
            String subName = "";
            String logoUrl = body.select(".companyLogo").attr("src");

            String companyUrl = "";
            String location = "";
            String scale = "";
            String industry = "";
            String nature = "";

            List<String> comTinyDec = body.select("body > div.main > div.mainLeft > div:nth-child(1) > table > tbody > tr").eachText();
            for (String s : comTinyDec) {
                if (s.startsWith("公司性质"))
                    nature = s.substring(s.indexOf("：") + 1);
                if (s.startsWith("公司规模"))
                    scale = s.substring(s.indexOf("：") + 1);
                if (s.startsWith("公司网站"))
                    companyUrl = s.substring(s.indexOf("：") + 1);
                if (s.startsWith("公司行业"))
                    industry = s.substring(s.indexOf("：") + 1);
                if (s.startsWith("公司地址"))
                    location = s.substring(s.indexOf("：") + 1);
            }


            String companyWeibo = "";
            String companyWeixin = "";
            String establishTime = "";
            String developmentStage = "";
            String stockCode = "";
            String description = body.select(".company-content").text();
            String workLocate = location;
            boolean nearSubway = false;
            boolean hasShuttleBus = false;
            String tags = "";
            String financingSituation = "";
            String operationSituation = "";
            String superCompanyBackground = "";
            String companyBackground = "";
            String companyBackgroundImgUrl = "";
            company.setName(name);
            company.setSubName(name);
            company.setLogoUrl(logoUrl);
            company.setCompanyUrl(companyUrl);
            company.setCompanyWeibo(companyWeibo);
            company.setCompanyWeixin(companyWeixin);
            company.setLocation(location);
            company.setEstablishTime(establishTime);
            company.setScale(scale);
            company.setIndustry(industry);
            company.setNature(nature);
            company.setDevelopmentStage(developmentStage);
            company.setStockCode(stockCode);
            company.setDescription(description);
            company.setWorkLocate(workLocate);
            company.setNearSubway(nearSubway);
            company.setHasShuttleBus(hasShuttleBus);
            company.setTags(tags);
            company.setFinancingSituation(financingSituation);
            company.setOperationSituation(operationSituation);
            company.setSuperCompanyBackground(superCompanyBackground);
            company.setCompanyBackground(companyBackground);
            company.setCompanyBackgroundImgUrl(companyBackgroundImgUrl);
        } catch (IOException e) {
            logger.error("getCompany(url : {}) method throw IOException : {}", url, e.getMessage());
        }
        return company;
    }

    @Override
    public void save(Company company) {
        companyRedisDao.save(JSONObject.toJSON(company).toString(), redisCompanyListKey);
        logger.info("保存成功: {}", JSONObject.toJSON(company).toString());
    }

    @Override
    public void trimList() {
        listRedisDao.clean(redisListQueueKey);
        logger.info("清除成功");
    }

    @Override
    public void trimCompanysList() {
        companyRedisDao.clean(redisCompanyListKey);
        logger.info("保存成功");
    }

    @Override
    public Long listSize() {
        return listRedisDao.size(redisListQueueKey);
    }

    @Override
    public Long companySize() {
        return companyRedisDao.size(redisCompanyListKey);
    }

    @Override
    public String popUrl() {
        return listRedisDao.pop(redisListQueueKey);
    }

    @Override
    public Company popCompany() {
        String comStr = companyRedisDao.pop(redisCompanyListKey);
        return JSONObject.parseObject(comStr, Company.class);
    }

    @Override
    public void saveAllCompanyToDB() {
        Company company = null;
        while (companySize() > 0) {
            company = popCompany();
            company = companyDao.save(company);
            System.out.println(company);
        }
    }

    @Override
    public Company saveTest(Company c) {
        return companyDao.save(c);
    }


}
