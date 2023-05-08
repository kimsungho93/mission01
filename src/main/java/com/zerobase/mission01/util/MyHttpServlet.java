package com.zerobase.mission01.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MyHttpServlet {

    public static void encoding(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html");
            req.setCharacterEncoding("utf8");
            resp.setCharacterEncoding("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static void redirect(HttpServletRequest req, HttpServletResponse resp, String page) {
        try {
            String url = req.getRequestURL().toString().replace(req.getRequestURI(),"") + req.getContextPath() + page;

            resp.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void forward(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, String page) {
        try {
            ServletContext app = servlet.getServletContext();
            RequestDispatcher dispatcher = app.getRequestDispatcher("/" + page);
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.getStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
