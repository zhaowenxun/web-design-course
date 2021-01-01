package com.staffmanage.controller;

import com.google.gson.Gson;
import com.staffmanage.dao.newHireDaoImpl;
import com.staffmanage.dao.newHiresDao;
import com.staffmanage.entity.staffxun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getnewHiresServlet")
public class getnewHiresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timeBegin = req.getParameter("beginTime");
        String timeEnd = req.getParameter("endTime");
        String getDepart=req.getParameter("positonName");
        System.out.println(timeBegin+" "+timeEnd+" "+getDepart);
        newHiresDao p= new newHireDaoImpl();
        List<staffxun> xun=p.getnewHires(timeBegin,timeEnd,getDepart);

        Gson gson= new Gson();
        String staffJson = gson.toJson(xun);
//        System.out.println(staffJson);
//        resp.getWriter().write(staffJson);

//        req.setAttribute("xun", xun);
//        req.getRequestDispatcher("personalReport.jsp").forward(req, resp);
    }
}
