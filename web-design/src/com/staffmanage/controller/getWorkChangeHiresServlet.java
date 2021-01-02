package com.staffmanage.controller;

import com.google.gson.Gson;
import com.staffmanage.dao.workChangeHiresDao;
import com.staffmanage.dao.workChangeHiresDaoImpl;
import com.staffmanage.entity.staffxun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getWorkChangeHiresServlet")
public class getWorkChangeHiresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");//设置uft-8编码
        req.setCharacterEncoding("UTF-8");

        String timeBegin = req.getParameter("beginTime");
        String timeEnd = req.getParameter("endTime");
        System.out.println("gangwei servlet"+timeBegin+" "+timeEnd);
        workChangeHiresDao p= new workChangeHiresDaoImpl();
        List<staffxun> xun=p.getWorkChangeHiresDao(timeBegin,timeEnd);

        Gson gson= new Gson();
        String staffJson = gson.toJson(xun);
//        System.out.println(staffJson);
        resp.getWriter().write(staffJson);

//        req.setAttribute("xun", xun);
//        req.getRequestDispatcher("personalReport.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
