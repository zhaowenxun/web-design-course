package com.staffmanage.controller;

import c.s.service.persionalService;
import c.s.service.persionalServiceImpl;
import com.staffmanage.dao.persionalReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/*
    designer: zhaowenxun
 */
@WebServlet("/getAllpersionalNumsServlet")
public class getAllpersionalNumsServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timeBegin = req.getParameter("beginTime");
        if(timeBegin==null){
            Calendar cale = null;
            cale = Calendar.getInstance();
            int year = cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            timeBegin=""+year+"-"+(month<10 ? "0"+month:month);
        }
        System.out.println("timeBegin"+timeBegin);
        persionalService q = new persionalServiceImpl();
        List<persionalReport> pRep= q.getStudentBySid(timeBegin);
       // System.out.println(pRep);
        req.setAttribute("pRep", pRep);
        req.getRequestDispatcher("personalReport.jsp").forward(req, resp);
        //转发
    }

}
