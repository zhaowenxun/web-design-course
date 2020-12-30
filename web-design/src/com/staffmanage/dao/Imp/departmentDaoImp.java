package com.staffmanage.dao.Imp;

import com.staffmanage.Utils.DbUtils;
import com.staffmanage.dao.departmentDao;
import com.staffmanage.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class departmentDaoImp implements departmentDao {

    @Override
    public void addDepartment(Department dep) {

    }

    @Override
    public void queryDepartment(Department dep) {

    }

    @Override
    public void deleteDepartment(Department dep) {

    }

    @Override
    public List<Department> getAllDepartment() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Department>  deps=new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select  *  from department";
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();

            while(rs.next())
            {
                Department dep=new Department();
                dep.setDepartmentNumber(rs.getString("dnum"));
                dep.setDepartmentName(rs.getString("dname"));
                dep.setDepartmentType(rs.getString("type"));
                dep.setDepartmentPhone(rs.getString("phone"));
                dep.setEstablishData(rs.getString("establishDate"));
                dep.setDescription(rs.getString("des"));
                deps.add(dep);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStatement(pstmt);
            DbUtils.closeConnection();
        }
        return  deps;
    }
}
