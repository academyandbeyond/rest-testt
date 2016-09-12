package com.resttest.framework.json.model;

import java.sql.Timestamp;

/**
 * Created by sreepadbhagwat on 9/9/16.
 */
public class RestTimer {

    private Timestamp starttime;
    private Timestamp endtime;

    public void setStartTime(Timestamp starttime){
        this.starttime=starttime;
    }

    public Timestamp getStartTime(){
        return starttime;
    }

    public void setEndTime(Timestamp endtime){
        this.starttime=starttime;
    }

    public Timestamp getEndTime(){
        return endtime;
    }

}
