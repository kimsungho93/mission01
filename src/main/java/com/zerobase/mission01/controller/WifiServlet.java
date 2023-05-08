package com.zerobase.mission01.controller;

import com.zerobase.mission01.model.BookmarkGroupService;
import com.zerobase.mission01.model.WifiService;
import com.zerobase.mission01.model.dto.WifiInfo;
import com.zerobase.mission01.util.DB;
import com.zerobase.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@WebServlet(name = "wifiServlet", urlPatterns = "/detail/*", loadOnStartup = 1)
public class WifiServlet extends HttpServlet {
    WifiService service = WifiService.getInstance();

    @Override
    public void init() {
        DB.open();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        MyHttpServlet.encoding(req, resp);

        String idx;
        double distance;

        String[] uri = req.getRequestURI().split("/");
        int maxIdxUri = uri.length - 1;
        if (isDouble(uri[maxIdxUri])) {
            idx = uri[maxIdxUri - 1];
            distance = Double.parseDouble(uri[maxIdxUri]);
        } else {
            idx = uri[maxIdxUri];
            distance = 0.0;
        }

        WifiInfo wi = service.select(URLDecoder.decode(idx, "UTF-8"));
        wi.setDistance(distance);

        req.setAttribute("wifiInfo", wi);
        req.setAttribute("bmgList", BookmarkGroupService.getInstance().selectAll());

        MyHttpServlet.forward(this, req, resp, "detail.jsp");
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
