package com.zerobase.mission01.model;

import com.zerobase.mission01.model.dao.Bookmark;

import java.util.List;

public class BookmarkService implements IBookmarkService {
    private final Bookmark dao;
    private BookmarkService() {
        this.dao = Bookmark.getInstance();
    }

    private static BookmarkService service;
    public static BookmarkService getInstance() {
        if (service == null) {
            service = new BookmarkService();
        }
        return service;
    }

    @Override
    public void deleteBookmarkGroup(int idxBookmarkGroup) {
        dao.deleteBookmarkGroup(idxBookmarkGroup);
    }

    @Override
    public void delete(int idx) {
        dao.delete(idx);
    }

    @Override
    public void insert(com.zerobase.mission01.model.dto.Bookmark b) {
        dao.insert(b);
    }

    @Override
    public List<com.zerobase.mission01.model.dto.Bookmark> selectAll() {
        return dao.selectAll();
    }

    @Override
    public com.zerobase.mission01.model.dto.Bookmark select(int idx) {
        return dao.select(idx);
    }
}

interface IBookmarkService {

    void deleteBookmarkGroup(int idxBookmarkGroup);
    void delete(int idx);
    void insert(com.zerobase.mission01.model.dto.Bookmark b);
    List<com.zerobase.mission01.model.dto.Bookmark> selectAll();
    com.zerobase.mission01.model.dto.Bookmark select(int idx);
}