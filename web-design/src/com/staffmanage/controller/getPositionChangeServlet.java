package com.staffmanage.controller;

import com.google.gson.Gson;
import com.staffmanage.dao.newHireDaoImpl;
import com.staffmanage.dao.newHiresDao;
import com.staffmanage.dao.positionChangeHiresDao;
import com.staffmanage.dao.positionChangeHiresDaoImpl;
import com.staffmanage.entity.staffxun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getPositionChangeServlet")
public class getPositionChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码
        req.setCharacterEncoding("UTF-8");

        String timeBegin = req.getParameter("beginTime");
        String timeEnd = req.getParameter("endTime");

        System.out.println("bumen Servlet: "+timeBegin+" "+timeEnd);
        positionChangeHiresDao p= new positionChangeHiresDaoImpl();
        List<staffxun> xun=p.getpositionChangeHires(timeBegin,timeEnd);

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
