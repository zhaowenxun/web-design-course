package com.staffmanage.dao;

import com.staffmanage.entity.Department;

import java.util.List;

public interface departmentDao {
    //添加部门
    void addDepartment(Department dep);

    //查询部门
    void queryDepartment(Department dep);

    //删除部门
    void deleteDepartment(Department dep);

    //获取所有部门
    List<Department> getAllDepartment();
}

