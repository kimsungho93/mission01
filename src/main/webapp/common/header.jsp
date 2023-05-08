<%@ page contentType="text/html;charset=UTF-8" %>
<% final String SITE_TITLE = "와이파이 정보 구하기"; %>
<% final String TITLE = request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle").toString() : SITE_TITLE; %>
<% final String ROOT = request.getContextPath() + "/"; %>

<!DOCTYPE html>
<html>
<head>
    <title><%= SITE_TITLE %></title>
    <meta charset="utf-8">
    <style>
        table {
            width : 100%;
        }
        tr td {
            border: 1px solid #e5e5e5;
            padding: 4px 13px;
        }
        tr:nth-child(2n) {
            background-color: #e1e1e1;
        }
        th {
            background-color: #00ac6f;
            color: #fff;
        }
        td {
            color: #000;
            font-weight: bold;
        }
        .list-no-data th {
            padding: 30px 0;
            background-color: #fff;
            color: #000;
            font-weight: bold;
        }
        .wifi-load {
            text-align: center;
        }
    </style>
</head>

