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

public class separatedHiresDaoImpl implements separatedHiresDao{
    @Override
    public List<staffxun> getSeparatedHires(String begin, String end, String option) {
        if(begin==null){
            Calendar now = Calendar.getInstance();
            begin=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-00";
            end=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-"+String.format("%02d",now.get(Calendar.DAY_OF_MONTH) );
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        System.out.println("lizhi Dao "+begin+" "+ end+" "+option);
        List<staffxun>  xun=new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select staffquit.id,dname,post.pname,staffquit.name,staffquit.sex,staffquit.quitDate,staffquit.type " +
                    "from staffquit,department,post " +
                    "where staffquit.did=department.dnum and staffquit.pid=post.pnum and  quitDate>= ? and quitDate <= ? ";
            if(option ==null || option.isEmpty())
            {
                sql+=" order by staffquit.id;";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, begin);
                pstmt.setString(2, end);
            }
            else{
                sql=sql+" and dname= ? order by staffquit.id";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, begin);
                pstmt.setString(2, end);
                pstmt.setString(3, option);
            }
            System.out.println(sql);
            rs=pstmt.executeQuery();

            while(rs.next())
            {
                staffxun per= new staffxun();
                per.setId(rs.getInt("id"));
                per.setDepart(rs.getString("dname"));
                per.setPost(rs.getString("pname"));
                per.setName(rs.getString("name"));
                per.setSex(rs.getString("sex"));
                per.setQdate(rs.getString("quitDate"));
                per.setDreason(rs.getString("type"));
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
