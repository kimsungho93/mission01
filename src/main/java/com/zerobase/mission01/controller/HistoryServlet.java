package com.zerobase.mission01.controller;

import com.zerobase.mission01.model.HistoryService;
import com.zerobase.mission01.model.dto.History;
import com.zerobase.mission01.util.MyDateTime;
import com.zerobase.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "historyServlet", urlPatterns = "/history", loadOnStartup = 3)
public class HistoryServlet extends HttpServlet {
    private final HistoryService service = HistoryService.getInstance();

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        MyHttpServlet.encoding(request, response);

        request.setAttribute("pageTitle", "위치 히스토리 목록");
        request.setAttribute("list", service.select());

        MyHttpServlet.forward(this, request, response, "_history/");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        service.delete(req.getParameter("idx"));

        resp.sendRedirect("history");
    }

    public void insertHistory(String lat, String lnt) {
        History h = new History();
        h.setLat(Double.parseDouble(lat));
        h.setLnt(Double.parseDouble(lnt));
        h.setRegDateTime(MyDateTime.getNow());

        service.insert(h);
    }

    public void destroy() {}
}