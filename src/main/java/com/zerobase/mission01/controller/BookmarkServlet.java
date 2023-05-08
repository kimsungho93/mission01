package com.zerobase.mission01.controller;

import com.zerobase.mission01.model.BookmarkService;
import com.zerobase.mission01.model.dto.Bookmark;
import com.zerobase.mission01.util.MyDateTime;
import com.zerobase.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="bookmarkServlet", urlPatterns = "/bookmark/*")
public class BookmarkServlet extends HttpServlet {
    BookmarkService service = BookmarkService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String idx = req.getParameter("idx");
        String uri = "";
        String title = "";

        if (idx == null) {
            req.setAttribute("list", service.selectAll());
            uri = "";
            title = "";

        } else {
            req.setAttribute("data", service.select(Integer.parseInt(idx)));
            uri = "detail.jsp";
            title = " 삭제";
        }

        req.setAttribute("pageTitle", "북마크" + title);

        MyHttpServlet.forward(this, req, resp, "/_bookmark/" + uri);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String[] uris = req.getRequestURI().split("/");
        int maxIdxUris = uris.length - 1;

        if ("add".equals(uris[maxIdxUris])) {
            Bookmark b = new Bookmark();
            b.setIdxBookMarkGroup(Integer.parseInt(req.getParameter("idxBmg")));
            b.setIdxWifi(req.getParameter("idxWifi"));
            b.setRegDatetime(MyDateTime.getNow());

            service.insert(b);

        } else if ("delete".equals(uris[maxIdxUris])) {
            service.delete(Integer.parseInt(req.getParameter("idx")));
        }

        req.setAttribute("list", service.selectAll());
        req.setAttribute("pageTitle", "북마크");

        MyHttpServlet.forward(this, req, resp, "/_bookmark/");
    }
}