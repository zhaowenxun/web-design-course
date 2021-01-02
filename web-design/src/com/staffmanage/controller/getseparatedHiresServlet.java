package com.staffmanage.controller;

import com.google.gson.Gson;
import com.staffmanage.dao.newHireDaoImpl;
import com.staffmanage.dao.newHiresDao;
import com.staffmanage.dao.separatedHiresDao;
import com.staffmanage.dao.separatedHiresDaoImpl;
import com.staffmanage.entity.staffxun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getseparatedHiresServlet")
public class getseparatedHiresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码
        req.setCharacterEncoding("UTF-8");

        String timeBegin = req.getParameter("beginTime");
        String timeEnd = req.getParameter("endTime");
        String getDepart=req.getParameter("positionName");

        System.out.println("lizhi "+timeBegin+" "+timeEnd+" "+getDepart);
        separatedHiresDao p= new separatedHiresDaoImpl();
        List<staffxun> xun=p.getSeparatedHires(timeBegin,timeEnd,getDepart);

        Gson gson= new Gson();
        String staffJson = gson.toJson(xun);
        System.out.println(staffJson);
        resp.getWriter().write(staffJson);

//        req.setAttribute("xun", xun);
//        req.getRequestDispatcher("personalReport.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
