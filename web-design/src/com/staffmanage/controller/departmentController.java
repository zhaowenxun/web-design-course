package com.staffmanage.controller;
import com.google.gson.Gson;
import com.staffmanage.dao.Imp.departmentDaoImp;
import com.staffmanage.dao.Imp.postDaoImp;
import com.staffmanage.dao.Imp.staffDaoImpDepartmentChange;
import com.staffmanage.dao.departmentDao;
import com.staffmanage.dao.postDao;
import com.staffmanage.dao.staffDao;
import com.staffmanage.entity.Department;
import com.staffmanage.entity.Post;
import com.staffmanage.entity.Staff;
import com.staffmanage.entity.view.changeDepartmentView;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Department")
public class departmentController extends HttpServlet {
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        String did = req.getParameter("did");
//        String dname = req.getParameter("dname");
//        String sid = req.getParameter("sid");
//        String sname = req.getParameter("sname");
//        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码
        //sid = "00000001";
        //sname = "方辰宇";
        //System.out.println(did);
        //System.out.println(dname);
        //System.out.println(sid);
        //System.out.println(sname);
        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码

        List<Post> postList;
        List<Department> departmentList;

        postDao pd = new postDaoImp();
        departmentDao dd = new departmentDaoImp();
        //staffDao sd = new staffDaoImpDepartmentChange();

//        postList = pd.getAllPost();
//        departmentList = dd.getAllDepartment();
        //staffList = sd.getByDidAndDnameAndSidAndSname(did,dname,sid,sname);
        departmentList= dd.getAllDepartment();
        /*
        for(Staff staff:staffList){
            for(Department department:departmentList){
                if(staff.getDid()==department.getDepartmentNumber()){
                    staff.setDid(department.getDepartmentName());
                }
            }
            for(Post post:postList){
                if(staff.getPid()==post.getPnum()){
                    staff.setPid(post.getPname());
                }
            }
        }

         */

        Gson gson= new Gson();
        String staffJson = gson.toJson(departmentList);
        System.out.println(staffJson);
        resp.getWriter().write(staffJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
