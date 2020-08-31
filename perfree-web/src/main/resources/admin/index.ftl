<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>Perfree</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"/>
        <meta name="renderer" content="webkit"/>
        <meta name="force-rendering" content="webkit"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <link href="/public/libs/mdui/css/mdui.min.css" rel="stylesheet"/>
        <link href="/admin/static/css/style.css" rel="stylesheet"/>
    </head>
    <body class="mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-layout-auto mdui-loaded mdui-drawer-body-left mdui-color-grey-50">
        <!-- 顶栏start -->
        <#include "layout/header.ftl">
        <!-- 顶栏end -->
        <!-- 左侧边栏start -->
        <#include "layout/sider.ftl">
        <!-- 左侧边栏end -->

        <!-- 内容start -->
        <div class="p-container">
            <div class="mdui-shadow-1 p-iframe-title" id="p-iframe-title">面板</div>
            <div class="p-iframe">
                <iframe id="iframe" scrolling="0" width="100%" height="100%" frameborder="0"></iframe>
            </div>
        </div>
        <!-- 内容ned -->

        <script src="/public/libs/jquery/jquery-3.5.1.min.js"></script>
        <script src="/public/libs/mdui/js/mdui.min.js"></script>
        <script src="/admin/static/js/main.js"></script>
    </body>
</html>