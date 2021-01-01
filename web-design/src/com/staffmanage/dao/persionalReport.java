package com.staffmanage.dao;
/*
    designer: zhaowenxun
 */
public class persionalReport {
    private String department;
    private int monthBeginNum;//月初人数
    private int monthEndnum;//月末人数
    private int thisMonthIn;//本月入职人数
    private int thisMonthOut;//本月离职人数
    private int getThisMonthChangeOut;//本月调出
    private int getThisMonthChangeIn;//本月调入
    private int graduteStudent;//研究生
    private int ungraduteStudent;//本科
    private int juniorStudent;//大专
    private int seninoHighStudent;//高中及以下

    public persionalReport(){

    }

    public persionalReport(String department, int monthBeginNum, int monthEndnum, int thisMonthIn, int thisMonthOut, int getThisMonthChangeOut, int getThisMonthChangeIn, int graduteStudent, int ungraduteStudent, int juniorStudent, int seninoHighStudent) {
        this.department = department;
        this.monthBeginNum = monthBeginNum;
        this.monthEndnum = monthEndnum;
        this.thisMonthIn = thisMonthIn;
        this.thisMonthOut = thisMonthOut;
        this.getThisMonthChangeOut = getThisMonthChangeOut;
        this.getThisMonthChangeIn = getThisMonthChangeIn;
        this.graduteStudent = graduteStudent;
        this.ungraduteStudent = ungraduteStudent;
        this.juniorStudent = juniorStudent;
        this.seninoHighStudent = seninoHighStudent;
    }

    public String getDepartment() {
        return department;
    }

    public int getMonthBeginNum() {
        return monthBeginNum;
    }

    public int getMonthEndnum() {
        return monthEndnum;
    }

    public int getThisMonthIn() {
        return thisMonthIn;
    }

    public int getThisMonthOut() {
        return thisMonthOut;
    }

    public int getGetThisMonthChangeOut() {
        return getThisMonthChangeOut;
    }

    public int getGetThisMonthChangeIn() {
        return getThisMonthChangeIn;
    }

    public int getGraduteStudent() {
        return graduteStudent;
    }

    public int getUngraduteStudent() {
        return ungraduteStudent;
    }

    public int getJuniorStudent() {
        return juniorStudent;
    }

    public int getSeninoHighStudent() {
        return seninoHighStudent;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMonthBeginNum(int monthBeginNum) {
        this.monthBeginNum = monthBeginNum;
    }

    public void setMonthEndnum(int monthEndnum) {
        this.monthEndnum = monthEndnum;
    }

    public void setThisMonthIn(int thisMonthIn) {
        this.thisMonthIn = thisMonthIn;
    }

    public void setThisMonthOut(int thisMonthOut) {
        this.thisMonthOut = thisMonthOut;
    }

    public void setGetThisMonthChangeOut(int getThisMonthChangeOut) {
        this.getThisMonthChangeOut = getThisMonthChangeOut;
    }

    public void setGetThisMonthChangeIn(int getThisMonthChangeIn) {
        this.getThisMonthChangeIn = getThisMonthChangeIn;
    }

    public void setGraduteStudent(int graduteStudent) {
        this.graduteStudent = graduteStudent;
    }

    public void setUngraduteStudent(int ungraduteStudent) {
        this.ungraduteStudent = ungraduteStudent;
    }

    public void setJuniorStudent(int juniorStudent) {
        this.juniorStudent = juniorStudent;
    }

    public void setSeninoHighStudent(int seninoHighStudent) {
        this.seninoHighStudent = seninoHighStudent;
    }
}
