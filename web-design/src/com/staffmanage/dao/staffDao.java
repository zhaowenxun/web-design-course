package com.staffmanage.dao;

import com.staffmanage.entity.Staff;
import com.staffmanage.entity.view.changeDepartmentView;

import java.util.List;

public interface staffDao {
    //按照id寻找
    void getByID(int id);
    //获取所有员工
    List<Staff> getAllStaff();
    //模糊查找
    List<Staff> getByDidAndDnameAndSidAndSname();

    List<changeDepartmentView> getByDidAndDnameAndSidAndSname(String did, String dname, String sid, String sname);
}
