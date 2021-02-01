package com.koreait.diaryapp;

public class Schedule {
    private int schedule_id;
    private int member_id;
    private int yy;
    private int mm;
    private int dd;
    private String msg;

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
