package com.staffmanage.dao.Imp;

import com.staffmanage.Utils.DbUtils;
import com.staffmanage.dao.staffDao;
import com.staffmanage.entity.Staff;
import com.staffmanage.entity.view.changeDepartmentView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class staffDaoImpDepartmentChange implements staffDao {

    @Override
    public void getByID(int id) {

    }

    @Override
    public List<Staff> getByDidAndDnameAndSidAndSname() {
        return null;
    }

    @Override
    public List<Staff> getAllStaff() {
        return null;
    }

    @Override
    public List<changeDepartmentView> getByDidAndDnameAndSidAndSname(String did, String dname, String sid, String sname) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet resultSet=null;
        List<changeDepartmentView> cdvArrayList = new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            String sql="SELECT * FROM staff,department,post WHERE staff.did=department.dnum and staff.pid=post.pnum";
            int i=1;
            if (did!=null&&!did.trim().isEmpty()){
                sql=sql+" and did="+did;
            }
            if (dname!=null&&!dname.trim().isEmpty()){
                sql=sql+" and dname="+dname;
            }
            if (sid!=null&&!sid.trim().isEmpty()){
                sql=sql+" and id="+sid;
            }
            if (sname!=null&&!sname.trim().isEmpty()){
                sql=sql+" and name="+sname;
            }

            pstmt=conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                changeDepartmentView view = new changeDepartmentView();
                view.setId(resultSet.getString("id"));
                view.setName(resultSet.getString("name"));
                view.setDid(resultSet.getString("did"));
                view.setPid(resultSet.getString("pid"));
                view.setDname(resultSet.getString("dname"));
                view.setPname(resultSet.getString("pname"));
                cdvArrayList.add(view);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            DbUtils.closeStatement(pstmt);
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeConnection();
        }
        return cdvArrayList;
    }
}
