package com.zerobase.mission01.model;

import com.zerobase.mission01.model.dao.BookmarkGroup;

import java.util.List;

public class BookmarkGroupService implements IBookmarkGroupService {
    private final BookmarkGroup dao;
    private BookmarkGroupService() {
        this.dao = BookmarkGroup.getInstance();
    }

    private static BookmarkGroupService service;
    public static BookmarkGroupService getInstance() {
        if (service == null) {
            service = new BookmarkGroupService();
        }
        return service;
    }

    @Override
    public void delete(int idx) {
        dao.delete(idx);
        BookmarkService.getInstance().deleteBookmarkGroup(idx);
    }

    @Override
    public void insert(com.zerobase.mission01.model.dto.BookmarkGroup b) {
        dao.insert(b);
    }

    @Override
    public void update(com.zerobase.mission01.model.dto.BookmarkGroup b) {
        dao.update(b);
    }

    @Override
    public com.zerobase.mission01.model.dto.BookmarkGroup select(int idx) {
        return dao.select(idx);
    }

    @Override
    public List<com.zerobase.mission01.model.dto.BookmarkGroup> selectAll() {
        return dao.selectAll();
    }
}

interface IBookmarkGroupService {

    void delete(int idx);
    void insert(com.zerobase.mission01.model.dto.BookmarkGroup b);
    void update(com.zerobase.mission01.model.dto.BookmarkGroup b);
    com.zerobase.mission01.model.dto.BookmarkGroup select(int idx);
    List<com.zerobase.mission01.model.dto.BookmarkGroup> selectAll();
}