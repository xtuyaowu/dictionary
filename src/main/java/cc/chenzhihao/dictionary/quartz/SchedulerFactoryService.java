package cc.chenzhihao.dictionary.quartz;

import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;
import java.util.Map;

/**
 * Des : <dictionary-scaffold> : 定时任务调度工厂业务接口
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/24 21:04
 */
public interface SchedulerFactoryService {

    /**
     * 获取SchedulerFactoryBean
     * @return SchedulerFactoryBean
     */
    SchedulerFactoryBean schedulerFactoryBean();

    /**
     * 获取应用中所有生存中的定时任务
     * @return 定时任务列表
     * @throws SchedulerException
     */
    List<Map<String, String>> joblist() throws SchedulerException;

    /**
     * 暂停某定时任务
     *
     * @param jobName  定时任务名
     * @param jobGroup 定时任务分组名
     * @return true=>成功. false=>定时任务不存在
     * @throws SchedulerException
     */
    boolean pauseJob(String jobName, String jobGroup) throws SchedulerException;

    /**
     * 恢复某暂停中的任务
     *
     * @param jobName      定时任务名
     * @param jobGroup     定时任务分组名
     * @param triggerName  定时任务触发器名
     * @param triggerGroup 定时任务触发器分组名
     * @return 1=>成功, 0=>任务处于非暂停状态,无需恢复. -1=>任务不存在. -2=>触发器不存在
     * @throws SchedulerException
     */
    int resumeJob(String jobName, String jobGroup, String triggerName, String triggerGroup) throws SchedulerException;

    /**
     * 删除任务
     *
     * @param jobName  定时任务名
     * @param jobGroup 定时任务分组名
     * @return 1=> 成功 . 0=>任务不存在. -1=>删除失败
     * @throws SchedulerException
     */
    int deleteJob(String jobName, String jobGroup) throws SchedulerException;

    /**
     * 更新任务的时间表达式
     *
     * @param triggerName    定时任务触发器名
     * @param triggerGroup   定时任务触发器分组名
     * @param cronExpression 新crontab表达式
     * @return 1=>成功. 0=>触发器不存在
     */
    int rescheduleJob(String triggerName, String triggerGroup, String cronExpression) throws SchedulerException;


}
