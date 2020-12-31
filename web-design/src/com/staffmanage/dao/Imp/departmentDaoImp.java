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
        Connection conn=null;//建立连接
        PreparedStatement pstmt=null;//查询
        //ResultSet rs=null;//查询结果

        try {
            conn= DbUtils.getConnection();//建立连接
            System.out.println("conn:"+conn);
            //String sql="select  *  from department";
            String sql="insert into department(dnum,dname,type,phone,des,parent,establishDate,fax) values(?,?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,dep.getDnum());
            pstmt.setString(2,dep.getDname());
            pstmt.setString(3,dep.getType());
            pstmt.setString(4,dep.getPhone());
            pstmt.setString(5,dep.getDes());
            pstmt.setString(6,dep.getParent());
            pstmt.setString(7,dep.getEstablishDate());
            pstmt.setString(8,dep.getFax());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch blockD
            e.printStackTrace();
        }finally
        {
            DbUtils.closeStatement(pstmt);
            DbUtils.closeConnection();
        }
    }




    @Override
    public void queryDepartment(Department dep) {

    }

    @Override
    public void deleteDepartment(Department dep) {

    }

    @Override
    public List<Department> getAllDepartment() {
        Connection conn=null;//建立连接
        PreparedStatement pstmt=null;//查询
        ResultSet rs=null;//查询结果
        List<Department>  deps=new ArrayList<>();//查询结果

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select  *  from department";
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();

            while(rs.next())
            {
                Department dep=new Department();
                dep.setDnum(rs.getString("dnum"));
                dep.setDname(rs.getString("dname"));
                dep.setType(rs.getString("type"));
                dep.setPhone(rs.getString("phone"));
                dep.setEstablishDate(rs.getString("establishDate"));
                dep.setDes(rs.getString("des"));
                dep.setParent(rs.getString("parent"));
                dep.setFax(rs.getString("fax"));
                deps.add(dep);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch blockD
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
