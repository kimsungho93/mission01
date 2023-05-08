package com.zerobase.mission01.controller;

import com.zerobase.mission01.model.BookmarkGroupService;
import com.zerobase.mission01.model.dto.BookmarkGroup;
import com.zerobase.mission01.util.MyDateTime;
import com.zerobase.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="bookmarkgroupServlet", urlPatterns = "/bookmarkgroup/*")
public class BookmarkGroupServlet extends HttpServlet {
    private final BookmarkGroupService service = BookmarkGroupService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String[] uris = req.getRequestURI().split("/");
        int maxIdxUris = uris.length - 1;
        String title = "";
        String uri = "";
        String cmd = "";

        if ("add".equals(uris[maxIdxUris])) {
            title = " 추가";
            uri = "detail.jsp";
            cmd = "add";

        } else if ("update".equals(uris[maxIdxUris])) {
            int idx = Integer.parseInt(req.getParameter("idx"));

            title = " 수정";
            uri = "detail.jsp";
            cmd = "update";

            BookmarkGroup bmg = service.select(idx);
            req.setAttribute("bmg", bmg);

        } else {
            req.setAttribute("list", service.selectAll());
        }

        req.setAttribute("pageTitle", "북마크 그룹" + title);
        req.setAttribute("cmd", cmd);

        MyHttpServlet.forward(this, req, resp, "/_bookmarkgroup/" + uri);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String cmd = req.getParameter("cmd");
        List<BookmarkGroup> list = null;

        if ("delete".equals(cmd)) {
            int idx = Integer.parseInt(req.getParameter("idx"));
            service.delete(idx);

        } else {
            BookmarkGroup b = new BookmarkGroup();
            b.setName(req.getParameter("name"));
            b.setMyOrder(Integer.parseInt(req.getParameter("order")));

            if ("add".equals(cmd)) {
                b.setRegDatetime(MyDateTime.getNow());
                service.insert(b);

            } else if ("update".equals(cmd)) {
                b.setIdx(Integer.parseInt(req.getParameter("idx")));
                b.setUpdDatetime(MyDateTime.getNow());
                service.update(b);
            }
        }

        req.setAttribute("list", service.selectAll());
        req.setAttribute("pageTitle", "북마크 그룹");

        MyHttpServlet.redirect(req, resp, "/bookmarkgroup/");
    }
}