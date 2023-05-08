package com.zerobase.mission01.model;

import com.zerobase.mission01.model.dao.History;

import java.util.List;

public class HistoryService implements IHistoryService {
    private static History dao;
    public HistoryService() {
        dao = History.getInstance();
    }

    private static HistoryService service;
    public static HistoryService getInstance() {
        if (service == null) {
            service = new HistoryService();
        }
        return service;
    }

    @Override
    public void insert(com.zerobase.mission01.model.dto.History h) {
        dao.insert(h);
    }

    @Override
    public void delete(String idx) {
        dao.delete(idx);
    }

    @Override
    public List<com.zerobase.mission01.model.dto.History> select() {
        return dao.deleteAll();
    }
}

interface IHistoryService {
    void insert(com.zerobase.mission01.model.dto.History h);
    List<com.zerobase.mission01.model.dto.History> select();
    void delete(String idx);
}