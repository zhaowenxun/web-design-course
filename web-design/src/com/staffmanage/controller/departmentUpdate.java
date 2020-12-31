package com.staffmanage.controller;
import com.google.gson.Gson;
import com.staffmanage.entity.Department;
import com.staffmanage.dao.departmentDao;
import com.staffmanage.dao.Imp.departmentDaoImp;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DepartmentUpdate")
public class departmentUpdate extends HttpServlet {
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getParameter("abc"));
        System.out.println(req.getParameter("a"));
//        Department dp= new Department();
//
//        dp.setDnum(req.getParameter("dnum"));
//        dp.setDname(req.getParameter("dname"));
//        dp.setType(req.getParameter("type"));
//        dp.setParent(req.getParameter("parent"));
//        dp.setFax(req.getParameter("fax"));
//        dp.setDes(req.getParameter("des"));
//        dp.setPhone(req.getParameter("phone"));
//        dp.setEstablishDate(req.getParameter("establishDate"));
//
//        departmentDao dDao=new departmentDaoImp();
//        dDao.addDepartment(dp);
//
//
//        System.out.println(dp.getDname()+dp.getDnum()+dp.getEstablishDate()+dp.getType());
//


        //System.out.println("gfdgfdgfd:"+dname);
        req.getRequestDispatcher("departmentManagement.jsp").forward(req,resp);


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


        //List<Post> postList;
        //List<Department> departmentList;

        //postDao pd = new postDaoImp();
        //departmentDao dd = new departmentDaoImp();
        //System.out.println("431254254356436345");
        //staffDao sd = new staffDaoImpDepartmentChange();

//        postList = pd.getAllPost();
//        departmentList = dd.getAllDepartment();
        //staffList = sd.getByDidAndDnameAndSidAndSname(did,dname,sid,sname);
        //departmentList= dd.getAllDepartment();
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

//        Gson gson= new Gson();
//        String staffJson = gson.toJson(departmentList);
//        System.out.println(staffJson);
//        resp.getWriter().write(staffJson);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
