package cc.chenzhihao.dictionary.service.impl;

import cc.chenzhihao.dictionary.domain.Company;
import cc.chenzhihao.dictionary.domain.DO;
import cc.chenzhihao.dictionary.service.PaService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/5 13:56
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaServiceImplTest {

    @Resource(name = "paService")
    private PaService paService;

    @Test
    public void testGetList() throws Exception {
//        List<String> urls = paService.showList();
//        System.out.println(urls.size());
//        System.out.println(urls);

        System.out.println(paService.getList());
    }

    @Test
    public void testPushListToRedis() throws Exception {
        List<String> urls = paService.getList();
        paService.pushListToRedis(urls);
    }

    @Test
    public void testShowList() throws Exception {
        System.out.println(paService.showList());
    }

    @Test
    public void testGetCompany() throws Exception {
        Company company = paService.getCompany("http://company.zhaopin.com/CC658408127.htm");
        System.out.println(new JSONObject(company).toString());
    }


    @Test
    public void testTTT() {
        DO d = new DO(paService);

        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        Thread t3 = new Thread(d);
        Thread t4 = new Thread(d);
        Thread t5 = new Thread(d);
        Thread t6 = new Thread(d);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testSave() throws Exception {
        Company company = paService.getCompany("http://company.zhaopin.com/CC658408127.htm");
        System.out.println(new JSONObject(company).toString());

        paService.save(company);

    }

    @Test
    public void testTrimList() throws Exception {

    }

    @Test
    public void testTrimCompanysList() throws Exception {
        paService.trimCompanysList();
    }

    @Test
    public void testListSize() throws Exception {

    }

    @Test
    public void testCompanySize() throws Exception {
        System.out.println(paService.companySize());
    }


    @Test
    public void test() {

//        paService.trimList();
//        paService.trimCompanysList();
//
//        List<String> urls = paService.getList();
//        System.out.println(urls);
//        paService.pushListToRedis(urls);
//
//        String url = null;
//        while (paService.listSize()!=0){
//            url = paService.popUrl();
//            Company company  = paService.getCompany(url);
//            paService.save(company);
//        }


//
//        List<String> s = paService.showCompanys();
//        for (String e:
//             s) {
//            System.out.println(e);
//        }
    }

    @Test
    public void testSaveAllCompanyToDB() throws Exception {
        paService.saveAllCompanyToDB();
    }

    @Test
    public void testSaveTest() throws Exception {
        Company company = new Company();
        company.setName("陈志昊");
        paService.saveTest(company);
    }
}