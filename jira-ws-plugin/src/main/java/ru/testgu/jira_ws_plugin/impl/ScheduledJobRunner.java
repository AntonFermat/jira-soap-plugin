package ru.testgu.jira_ws_plugin.impl;

import com.atlassian.scheduler.JobRunner;
import com.atlassian.scheduler.JobRunnerRequest;
import com.atlassian.scheduler.JobRunnerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testgu.jira_ws_plugin.api.ExchangeRateService;

import javax.annotation.Nullable;
import javax.inject.Named;

@Named("scheduledJobRunner")
public class ScheduledJobRunner implements JobRunner {

    private static final Logger log = LoggerFactory.getLogger(ScheduledJobRunner.class);

    private final ExchangeRateService exchangeRateService;

    public ScheduledJobRunner(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Nullable
    @Override
    public JobRunnerResponse runJob(JobRunnerRequest jobRunnerRequest) {
        log.info("Executing a scheduled job");
        if (exchangeRateService != null) {
            exchangeRateService.exchangeRateUSD();
        }
        return JobRunnerResponse.success();
    }
}
