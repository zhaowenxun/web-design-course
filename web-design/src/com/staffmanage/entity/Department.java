package com.staffmanage.entity;

public class Department {
    private String dnum;
    private String dname;
    private String type;
    private String phone;
    private String establishDate;
    private String des;
    private String parent;
    private String fax;

    public Department(String dnum, String dname, String type, String phone, String establishData, String des, String parent, String fax) {
        this.dnum = dnum;
        this.dname = dname;
        this.type = type;
        this.phone = phone;
        this.establishDate = establishDate;
        this.des = des;
        this.parent = parent;
        this.fax = fax;
    }
    public Department() {
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }




    public String getDnum() {
        return dnum;
    }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}



