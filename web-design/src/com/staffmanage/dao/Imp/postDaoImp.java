package com.staffmanage.dao.Imp;

import com.staffmanage.Utils.DbUtils;
import com.staffmanage.dao.postDao;
import com.staffmanage.entity.Department;
import com.staffmanage.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class postDaoImp implements postDao {
    @Override
    public List<Post> getAllPost() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Post> posts=new ArrayList<>();

        try {
            conn= DbUtils.getConnection();
            System.out.println("conn:"+conn);
            String sql="select  *  from post";
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();

            while(rs.next())
            {
                Post post=new Post();
                post.setPnum(rs.getString("pnum"));
                post.setPname(rs.getString("pname"));
                post.setAuthorizedStrength(rs.getInt("authorizedStrength"));
                post.setType(rs.getString("type"));
                posts.add(post);
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
        return  posts;
    }
}
