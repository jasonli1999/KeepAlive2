package com.sdk.keepbackground.watch;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.sdk.keepbackground.work.DaemonEnv;

/**
 * Android 5.0+ 使用的 JobScheduler.
 * 运行在 :watch 子进程中.
 */


public class JobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("sj_keep", "JobSchedulerService  onStartJob 启动。。。。");
        DaemonEnv.startServiceSafely(JobSchedulerService.this, WatchDogService.class);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("sj_keep", "JobSchedulerService  onStopJob 停止。。。。");
        return false;
    }
}
