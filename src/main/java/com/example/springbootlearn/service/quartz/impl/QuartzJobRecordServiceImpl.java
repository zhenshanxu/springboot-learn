package com.example.springbootlearn.service.quartz.impl;

import com.example.springbootlearn.dao.quartz.QuartzJobRecordDao;
import com.example.springbootlearn.dao.quartz.QuartzTaskJobDao;
import com.example.springbootlearn.entity.quartz.QuartzJobRecordBean;
import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;
import com.example.springbootlearn.service.quartz.QuartzJobRecordService;
import com.example.springbootlearn.utils.enumType.Constant;
import com.example.springbootlearn.utils.quartz.QuartzUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:11:29
 * @Version 1.0
 */

@Slf4j
@Service
public class QuartzJobRecordServiceImpl implements QuartzJobRecordService {

    @Autowired
    private Scheduler scheduler;

    private final QuartzJobRecordDao quartzJobRecordDao;

    private final QuartzTaskJobDao quartzTaskJobDao;

    public QuartzJobRecordServiceImpl(QuartzJobRecordDao quartzJobRecordDao, QuartzTaskJobDao quartzTaskJobDao) {
        this.quartzJobRecordDao = quartzJobRecordDao;
        this.quartzTaskJobDao = quartzTaskJobDao;
    }

    /**
     * 添加定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    @Override
    public Map<String, Object> addExecuteRecord(QuartzJobRecordBean quartzJobRecordBean) {
        quartzJobRecordDao.addExecuteRecord(quartzJobRecordBean);
        return new HashMap<>();
    }

    /**
     * 修改定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    @Override
    public Map<String, Object> updateExecuteRecord(QuartzJobRecordBean quartzJobRecordBean) {
        quartzJobRecordDao.updateExecuteRecord(quartzJobRecordBean);
        return new HashMap<>();
    }

    /**
     * 查询定时任务执行记录
     *
     * @param quartzJobRecordBean
     * @return
     */
    @Override
    public List<QuartzJobRecordBean> queryExecuteRecord(QuartzJobRecordBean quartzJobRecordBean) {
        if (quartzJobRecordBean.getPage() != 0) {
            quartzJobRecordBean.setPage((quartzJobRecordBean.getPage() - 1) * quartzJobRecordBean.getPageSize());
        }
        return quartzJobRecordDao.queryExecuteRecord(quartzJobRecordBean);
    }

    /**
     * 创建执行定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> createJob(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            QuartzUtils.createScheduleJob(scheduler, quartzTaskJobBean);
            log.info("【Quartz】定时任务【" + quartzTaskJobBean.getJobName() + "】创建完成,任务启动!");
            // 更新 任务状态
            quartzTaskJobDao.updateJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()).setStatus(1));
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 暂停定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> pauseJob(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            QuartzUtils.pauseScheduleJob(scheduler, quartzTaskJobBean.getJobName());
            log.info("【Quartz】定时任务【" + quartzTaskJobBean.getJobName() + "】任务暂停成功!");
            // 更新 任务状态
            quartzTaskJobDao.updateJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()).setStatus(0));
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 继续执行任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> resume(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            // 查询任务是否存在，不存在则创建，
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(quartzTaskJobBean.getJobName()));
            if (jobDetail == null) {
                // 定时任务重新构建
                QuartzUtils.createScheduleJob(scheduler, quartzTaskJobBean);
            } else {
                // 继续定时任务
                QuartzUtils.resumeScheduleJob(scheduler, quartzTaskJobBean.getJobName());
            }
            log.info("【Quartz】定时任务【" + quartzTaskJobBean.getJobName() + "】任务继续执行!");
            quartzTaskJobDao.updateJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()).setStatus(1));
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 运行一次任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> runOnce(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            QuartzUtils.runOnce(scheduler, quartzTaskJobBean.getJobName());
            log.info("【Quartz】定时任务【" + quartzTaskJobBean.getJobName() + "】执行一次完成!");
            quartzTaskJobDao.updateJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()).setStatus(-1));
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 修改更新定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> updateJob(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            QuartzUtils.updateScheduleJob(scheduler, quartzTaskJobBean);
            log.info("【Quartz】定时任务【" + quartzTaskJobBean.getJobName() + "】更新完成,定时任务启动!");
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 根据定时任务名称从调度器中删除定时任务
     *
     * @param quartzTaskJob
     * @return
     */
    @Override
    public Map<String, Object> deleteScheduleJob(QuartzTaskJobBean quartzTaskJob) {
        try {
            QuartzTaskJobBean quartzTaskJobBean = getQuartzTaskJobInfo(quartzTaskJob);
            QuartzUtils.deleteScheduleJob(scheduler, quartzTaskJobBean.getJobName());
            log.info("【Quartz】 定时任务【" + quartzTaskJobBean.getJobName() + "】任务终止成功！");
            quartzTaskJobDao.updateJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()).setStatus(0));
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put(Constant.ERROR_VALUE, e.getMessage());
            }};
        }
        return new HashMap<>();
    }

    /**
     * 查询quartz定时任务信息
     *
     * @param quartzTaskJob
     * @return
     */
    private QuartzTaskJobBean getQuartzTaskJobInfo(QuartzTaskJobBean quartzTaskJob) {
        List<QuartzTaskJobBean> quartzTaskJobList = quartzTaskJobDao.queryJob(new QuartzTaskJobBean().setId(quartzTaskJob.getId()));
        Assert.isTrue(!CollectionUtils.isEmpty(quartzTaskJobList), "【Quartz】 id =【" + quartzTaskJob.getId() + "】定时任务查询有误!");
        return quartzTaskJobList.get(0);
    }


}
