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

public class positionChangeHiresDaoImpl implements positionChangeHiresDao{
    @Override
    public List<staffxun> getpositionChangeHires(String begin, String end) {
        if(begin==null){
            Calendar now = Calendar.getInstance();
            begin=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-00";
            end=""+now.get(Calendar.YEAR)+"-"+String.format("%02d",now.get(Calendar.MONTH) + 1)+"-"+String.format("%02d",now.get(Calendar.DAY_OF_MONTH) );
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        System.out.println("bumen Dao "+begin+" "+ end);
        List<staffxun>  xun=new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="SELECT departmenttransfer.sid,f.dname,s.dname as pname,staff.name,sex,tdate as quitDate,reason as type "+
                    "from departmenttransfer,department f,department s,staff " +
                    "where (departmenttransfer.sid=staff.id and departmenttransfer.did1=f.dnum and departmenttransfer.did2=s.dnum and tdate> ? and tdate < ? )" +
                    "order by departmenttransfer.sid; ";
            pstmt=conn.prepareStatement(sql);
             System.out.println(sql);
            pstmt.setString(1, begin);
            pstmt.setString(2, end);
            rs=pstmt.executeQuery();

            while(rs.next())
            {
                staffxun per= new staffxun();
                per.setId(rs.getInt("sid"));
                per.setDepart(rs.getString("dname"));
                per.setYdepart(rs.getString("pname"));
                per.setPost(rs.getString("pname"));
                per.setName(rs.getString("name"));
                per.setSex(rs.getString("sex"));
                per.setDdate(rs.getString("quitDate"));
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
