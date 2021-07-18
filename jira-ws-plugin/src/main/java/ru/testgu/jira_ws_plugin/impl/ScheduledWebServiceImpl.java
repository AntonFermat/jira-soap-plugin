package ru.testgu.jira_ws_plugin.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.scheduler.SchedulerService;
import com.atlassian.scheduler.SchedulerServiceException;
import com.atlassian.scheduler.config.JobConfig;
import com.atlassian.scheduler.config.JobId;
import com.atlassian.scheduler.config.JobRunnerKey;
import com.atlassian.scheduler.config.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testgu.jira_ws_plugin.api.ScheduledWebService;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Random;

@ExportAsService
@Named("scheduledWebService")
public class ScheduledWebServiceImpl implements ScheduledWebService, LifecycleAware {

    private static final Logger log = LoggerFactory.getLogger(ScheduledWebServiceImpl.class);

    private static final Random RANDOM = new Random();
    private static final int MIN_DELAY = 15000;
    private static final int MAX_JITTER = 10000;

    // The minimum interval is 1 minute
    private static final long INTERVAL = 1 * 60 * 1000l;

    private final JobRunnerKey jobRunnerKey = JobRunnerKey.of(ScheduledWebServiceImpl.class.getName() + ":instance");
    private JobId jobId;

    @ComponentImport
    private final SchedulerService schedulerService;
    private final ScheduledJobRunner scheduledJobRunner;

    @Inject
    public ScheduledWebServiceImpl(SchedulerService schedulerService, ScheduledJobRunner scheduledJobRunner) {
        this.schedulerService = schedulerService;
        this.scheduledJobRunner = scheduledJobRunner;
    }

    @Override
    public void onStart() {
        reschedule();
    }

    @Override
    public void reschedule() {
        final int jitter = RANDOM.nextInt(MAX_JITTER);
        final Date firstRun = new Date(System.currentTimeMillis() + MIN_DELAY + jitter);

        log.info("Register scheduled web service");
        schedulerService.registerJobRunner(jobRunnerKey, scheduledJobRunner);
        JobConfig jobConfig = JobConfig.forJobRunnerKey(jobRunnerKey)
                .withSchedule(Schedule.forInterval(INTERVAL, firstRun));
        try {
            jobId = schedulerService.scheduleJobWithGeneratedId(jobConfig);
        } catch (SchedulerServiceException e) {
            log.error("Unable to create schedule task");
        }
    }

    @PreDestroy
    public void onStop() {
        log.info("Unregister scheduled web service");
        if (jobId != null) {
            schedulerService.unscheduleJob(jobId);
            schedulerService.unregisterJobRunner(jobRunnerKey);
        }
    }
}
