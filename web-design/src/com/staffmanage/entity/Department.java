package com.staffmanage.entity;

public class Department {
    private String departmentNumber;
    private String departmentName;
    private String departmentType;
    private String departmentPhone;
    private String establishData;
    private String Description;

    public Department(String departmentNumber, String departmentName, String departmentType, String departmentPhone, String establishData, String description) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.departmentType = departmentType;
        this.departmentPhone = departmentPhone;
        this.establishData = establishData;
        Description = description;
    }

    public Department() {

    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    public String getEstablishData() {
        return establishData;
    }

    public void setEstablishData(String establishData) {
        this.establishData = establishData;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
