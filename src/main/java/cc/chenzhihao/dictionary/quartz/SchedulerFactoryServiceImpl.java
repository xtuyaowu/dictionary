package cc.chenzhihao.dictionary.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Des : <dictionary-scaffold> : 定时任务调度工厂业务接口实现
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/24 21:14
 */
@Service(value = "schedulerFactoryService")
public class SchedulerFactoryServiceImpl implements SchedulerFactoryService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public SchedulerFactoryBean schedulerFactoryBean() {
        return this.schedulerFactoryBean;
    }

    @Override
    public List<Map<String, String>> joblist() throws SchedulerException {
        List<Map<String, String>> jobList = new ArrayList<>();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        Map<String, String> job;
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                job = new HashMap<>();
                job.put("jobName", jobKey.getName());
                job.put("jobGroup", jobKey.getGroup());
                job.put("trigger", trigger.getKey().toString());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.put("status", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.put("cronExpression", cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    @Override
    public boolean pauseJob(String jobName, String jobGroup) throws SchedulerException {
        boolean flag = false;
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            scheduler.pauseJob(jobKey);
            flag = true;
        }
        return flag;
    }

    @Override
    public int resumeJob(String jobName, String jobGroup, String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        if (!scheduler.checkExists(jobKey)) {
            //任务不存在
            return -1;
        }
        if (!scheduler.checkExists(triggerKey)) {
            //触发器不存在
            return -2;
        }
        if (scheduler.getTriggerState(triggerKey).equals(Trigger.TriggerState.PAUSED)) {
            //暂停成功
            scheduler.resumeJob(jobKey);
            return 1;
        }
        //任务处于非暂停状态,什么都没干
        return 0;
    }

    @Override
    public int deleteJob(String jobName, String jobGroup) throws SchedulerException {
        //删除失败
        int flag = -1;
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            if (scheduler.deleteJob(jobKey))
                //删除成功
                flag = 1;
        } else
            //任务不存在
            flag = 0;
        return flag;
    }

    @Override
    public int rescheduleJob(String triggerName, String triggerGroup, String cronExpression) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null)
            return 0;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        trigger = trigger.getTriggerBuilder()
                .withIdentity(triggerKey)
                .withSchedule(scheduleBuilder).build();
        Date date = scheduler.rescheduleJob(triggerKey, trigger);
        System.out.println(date);
        return 1;
    }
}