package com.zerobase.mission01.model;

import com.zerobase.mission01.model.dao.Wifi;
import com.zerobase.mission01.model.dto.WifiInfo;

import java.util.List;

public class WifiService implements IWifiService {
    private final Wifi dao;
    private WifiService() {
        this.dao = Wifi.getInstance();
    }

    private static WifiService service;
    public static WifiService getInstance() {
        if (service == null) {
            service = new WifiService();
        }
        return service;
    }

    @Override
    public int insert(List<WifiInfo> list) {
        if (list == null || list.size() == 0) return 0;
        int insertCnt = 0;

        for (int i = 0; i < list.size(); i += 1000) {
            insertCnt += dao.insertAll(list.subList(i,  Math.min(1000 + i, list.size())));
        }

        return insertCnt;
    }

    @Override
    public WifiInfo select(String idx) {
        return dao.select(idx);
    }

    @Override
    public List<WifiInfo> select(double lat, double lnt) {
        return dao.selectAll(lat, lnt);
    }
}

interface IWifiService {
    WifiInfo select(String idx);
    List<WifiInfo> select(double lat, double lnt);
    int insert(List<WifiInfo> list);
}