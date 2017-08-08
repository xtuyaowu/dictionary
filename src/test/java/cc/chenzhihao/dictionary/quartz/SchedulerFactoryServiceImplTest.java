package cc.chenzhihao.dictionary.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Des : <dictionary-scaffold> : SchedulerFactoryService 单元测试
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/24 22:09
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerFactoryServiceImplTest {

    @Autowired
    private SchedulerFactoryService schedulerFactoryService;



    @Test
    public void testJoblist() throws Exception {
        System.out.println(schedulerFactoryService.joblist().toString());
    }
}