package com.staffmanage.dao;

import com.staffmanage.Utils.DbUtils;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    designer:zhaowenxun
    function:sql语句获取查询数据
 */
public class persionalServiceDaoImpl implements persionalServiceDao {

    @Override
    public List<persionalReport> getStudentBySid(String beginTime) {

        beginTime+="-00";//要查询的月初
        int year=(beginTime.charAt(0)-'0')*1000+(beginTime.charAt(1)-'0')*100+(beginTime.charAt(2)-'0')*10+(beginTime.charAt(3)-'0');
        int month=(beginTime.charAt(5)-'0')*10+(beginTime.charAt(6)-'0');
        month++;
        year+=month/13;
        month%=13;
        String endTime = ""+year+"-"+(month<10 ? "0"+month:month)+"-00"; //下个月月初
        HashMap<String,Integer> idBook=new HashMap<String, Integer>();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<persionalReport>  nums=new ArrayList<>();
        System.out.println(beginTime+" "+endTime);
        try {
            conn=DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select  dname from department order by dnum;";//先把所有部门名称导出，建立一个空表，然后在空表中加数据
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();//第一次查询 查询部门名称
            int t=0;
            while(rs.next())
            {
                persionalReport  psn=new persionalReport();
                if(rs.getString("dname")==null)break;
                psn.setDepartment(rs.getString("dname"));
                nums.add(psn);
                idBook.put(rs.getString("dname"),t++);//记录当前列所在List中对应位置
            }
            rs.close();

             sql="select dname,count(distinct staff.id) as 'monthBegin' from staff " +
                     "left outer join staffquit on (staff.id=staffquit.id)" +
                     "left outer join departmenttransfer on (staff.id=departmenttransfer.sid) " +
                     "left outer join department on (staff.did=department.dnum) " +
                     "where(staff.entryDate < ? and (quitDate is null or quitDate > ?) and ((tdate is null or tdate > ? )or(staff.did=departmenttransfer.did2 and tdate> ?)))" +
                     "group by staff.did order by staff.did;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, beginTime);
            pstmt.setString(2, beginTime);
            pstmt.setString(3, beginTime);
            pstmt.setString(4, beginTime);
            System.out.print("1");
            rs=pstmt.executeQuery();//第二次查询 查询月初人数
            while(rs.next())
            {
                if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setMonthBeginNum(rs.getInt("monthBegin"));
            }
            System.out.print(2);
            rs.close();

            sql="select dname,count(distinct staff.id) as 'monthEnd'," +
                    "count(case when staff.highestEducation ='研究生' then 1 end) as 'yjs',count(case when staff.highestEducation ='本科' then 1 end) as 'bks'," +
                    "count(case when staff.highestEducation ='大专' then 1 end) as 'dz',count(case when staff.highestEducation ='高中及以下' then 1 end ) as 'gz'"+
                    "from staff "+
                    "left outer join staffquit on (staff.id=staffquit.id)" +
                    "left outer join departmenttransfer on (staff.id=departmenttransfer.sid) " +
                    "left outer join department on (staff.did=department.dnum) " +
                    "where(staff.entryDate < ? and (quitDate is null or quitDate > ?) and ((tdate is null or tdate > ? )or(staff.did=departmenttransfer.did2 and tdate> ?)))" +
                    "group by staff.did order by staff.did;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, endTime);
            pstmt.setString(2, endTime);
            pstmt.setString(3, endTime);
            pstmt.setString(4, endTime);

            rs=pstmt.executeQuery();//第三次查询 查询月末人数,顺带统计月末成员的学历水平
            while(rs.next())
            {
                if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setMonthEndnum(rs.getInt("monthEnd"));
                nums.get(t).setGraduteStudent(rs.getInt("yjs"));
                nums.get(t).setUngraduteStudent(rs.getInt("bks"));
                nums.get(t).setJuniorStudent(rs.getInt("dz"));
                nums.get(t).setSeninoHighStudent(rs.getInt("gz"));
            }
            System.out.print(3);
            rs.close();

            sql="select dname,count(*) as 'lz'" +
                    "from staffquit, department " +
                    "where (staffquit.did=department.dnum and quitDate > ? and quitDate < ?)" +
                    "group by staffquit.did order by staffquit.did;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, beginTime);
            pstmt.setString(2, endTime);

            rs=pstmt.executeQuery();//第四次查询 查询本月离职人员
            while(rs.next())
            {
                if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setThisMonthOut(rs.getInt("lz"));
            }
            System.out.print(4);
            rs.close();

            sql="select dname,count(*) as 'rz'" +
                    "from staff, department " +
                    "where (staff.did=department.dnum and entryDate>? and entryDate <? )" +
                    "group by staff.did order by staff.did;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, beginTime);
            pstmt.setString(2, endTime);

            rs=pstmt.executeQuery();//第五次查询 查询本月入职人员
            while(rs.next())
            {
            if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setThisMonthIn(rs.getInt("rz"));
            }
            System.out.print(5);
            rs.close();

            sql="select dname,count(*) as 'dc'" +
                    "from departmenttransfer, department " +
                    "where (departmenttransfer.did1=department.dnum and tdate>? and tdate <? )\n" +
                    "group by department.dnum order by department.dnum;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, beginTime);
            pstmt.setString(2, endTime);

            rs=pstmt.executeQuery();//第六次查询 查询本月调出
            while(rs.next())
            {
                if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setGetThisMonthChangeOut(rs.getInt("dc"));
            }
            System.out.print(6);
            rs.close();

            sql="select dname,count(*) as 'dr'" +
                    "from departmenttransfer, department " +
                    "where (departmenttransfer.did2=department.dnum and tdate>? and tdate <? )\n" +
                    "group by department.dnum order by department.dnum;";//
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, beginTime);
            pstmt.setString(2, endTime);

            rs=pstmt.executeQuery();//第七次查询 查询本月调入
            while(rs.next())
            {
                if(rs.getString("dname")==null)break;
                t=idBook.get(rs.getString("dname"));
                nums.get(t).setGetThisMonthChangeIn(rs.getInt("dr"));
            }
            System.out.print(7);
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStatement(pstmt);
            DbUtils.closeConnection();
        }
        return  nums;
    }

}
