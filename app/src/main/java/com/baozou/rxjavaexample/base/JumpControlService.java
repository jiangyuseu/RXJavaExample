package com.baozou.rxjavaexample.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baozou.rxjavaexample.units.english.EnglishActivity;
import com.baozou.rxjavaexample.units.exam.ExamActivity;
import com.baozou.rxjavaexample.units.forasked.ForaskedActivity;
import com.baozou.rxjavaexample.units.maths.MathsActivity;
import com.baozou.rxjavaexample.units.politics.PoliticsActivity;
import com.baozou.rxjavaexample.units.share.ExperienceShareActivity;
import com.baozou.rxjavaexample.units.special.SpecialActivity;
import com.baozou.rxjavaexample.units.video.view.activity.VideosActivity;

/**
 * Created by lenovo on 2016/4/19.
 * 统一的跳转控制器，控制items跳转到哪个activity
 */
public class JumpControlService extends Service {

    private String url;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra("url");
        if (url.startsWith("aixue://")) {
            String activity = url.split("//")[1];
            if (activity.equals("a=maths")) {
                Intent mathIntent = new Intent(this, MathsActivity.class);
                mathIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mathIntent);
            } else if (activity.equals("a=english")) {
                Intent eIntent = new Intent(this, EnglishActivity.class);
                eIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(eIntent);
            } else if (activity.equals("a=politics")) {
                Intent pIntent = new Intent(this, PoliticsActivity.class);
                pIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(pIntent);
            } else if (activity.equals("a=special")) {
                Intent sIntent = new Intent(this, SpecialActivity.class);
                sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(sIntent);
            } else if (activity.equals("a=video")) {
                Intent vIntent = new Intent(this, VideosActivity.class);
                vIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(vIntent);
            } else if (activity.equals("a=exam")) {
                Intent aIntent = new Intent(this, ExamActivity.class);
                aIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(aIntent);
            } else if (activity.equals("a=experience_share")) {
                Intent aIntent = new Intent(this, ExperienceShareActivity.class);
                aIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(aIntent);
            } else if (activity.equals("a=forasked")) {
                Intent aIntent = new Intent(this, ForaskedActivity.class);
                aIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(aIntent);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
