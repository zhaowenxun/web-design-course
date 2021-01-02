package com.staffmanage.dao;

import com.staffmanage.Utils.DbUtils;
import com.staffmanage.entity.staffxun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class workChangeHiresDaoImpl implements workChangeHiresDao{

    @Override
    public List<staffxun> getWorkChangeHiresDao(String begin, String end) {
        if(begin==null){
            Calendar now = Calendar.getInstance();
            begin=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-00";
            end=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-"+String.format("%02d",now.get(Calendar.DAY_OF_MONTH) );
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        System.out.println("岗位调动Dao "+begin+" "+ end+" ");
        List<staffxun>  xun=new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select staff.id,dname,f.pname as fpname,s.pname as spname,staff.name,staff.sex,tdate,reason " +
                    " from staff,department,post f,post s,posttransfer  " +
                    " where staff.id=posttransfer.sid and posttransfer.pid1=f.pnum and posttransfer.pid2=s.pnum and staff.did=department.dnum and tdate > ? and tdate < ? " +
                    " order by staff.id;  ";
            pstmt=conn.prepareStatement(sql);
            // System.out.println(sql);
            pstmt.setString(1, begin);
            pstmt.setString(2, end);
            rs=pstmt.executeQuery();

            while(rs.next())
            {
                staffxun per= new staffxun();
                per.setId(rs.getInt("id"));
                per.setDepart(rs.getString("dname"));
                per.setYpost(rs.getString("fpname"));
                per.setPost(rs.getString("spname"));
                per.setName(rs.getString("name"));
                per.setSex(rs.getString("sex"));
                per.setDdate(rs.getString("tdate"));
                per.setDreason(rs.getString("reason"));
                xun.add(per);
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
        return  xun;
    }
}
