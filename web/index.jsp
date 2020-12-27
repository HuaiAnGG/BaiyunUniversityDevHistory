<%--
  Created by IntelliJ IDEA.
  User: huaian
  Date: 2020/12/23
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/css/style.css"
          type="text/css"/>
    <link rel="stylesheet" href="${ctx }/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx }/css/pageStyle.css">
    <link rel="stylesheet" href="${ctx}/js/bootstrap/css/bootstrap.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/paging.js"></script>
    <script type="text/javascript" src="${ctx}/js/template.js"></script>

</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">白云学院发展校史
        </strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">

        <%--        <div class="am-u-sm-12 am-u-md-3">--%>

        <%--        </div>--%>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" placeholder="请输入关键字..." style="z-index: 0;">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div style="width: 100%; height: 100%; display: flex; flex: 100%">
    <div style="width: 100px; height: 100%; margin: 10px 5px 0px 10px; background-color:white;">
        <div class="am-u-sm-12 am-u-md-6" style="display: flex; flex-direction: row;">
            <div class="am-btn-toolbar"
                 style="width:550px; display: flex; flex-direction: row; justify-content: space-around;">
                <%--分类(年份)标签--%>
                <div id="year_selected" style="width: 100%">
                </div>
            </div>
        </div>
    </div>
    <%--数据展示--%>
    <div class="goods_list" id="dev_hist_list" style="flex: 1;">

    </div>

</div>

<div id="modal_view">
    <div id="modal_content2" style="height: 90%; width: 90%; margin: 5%; display: none;">
        <div class="edit_content" style="background-color:#fff; height: 80%; width: 80%; position: relative;">
            <div id="close2" style="position:absolute; right: 0px; top: 0px; color: red;">
                <span class="glyphicon glyphicon-remove btn-lg" aria-hidden="true"></span>
            </div>
            <%--文章内容显示--%>
            <div id="article_content" style="width:90%; height:90%; margin: 20px auto; overflow-y: scroll;"></div>
        </div>
    </div>
</div>

<%--年份选择模板--%>
<script id="year_selected_template" type="text/html">
    <a href="index.jsp" style="text-decoration:none; padding-top: 15px;">
        <span class="label label-primary btn-lg">全部</span>
    </a>
    {{each list as ele}}
    <div style="width: 100%; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</div>
    <a href="index.jsp?cid={{ele.cid}}" style="text-decoration:none;">
        <span class="label label-primary btn-lg">{{ele.cname}}</span>
    </a>
    {{/each}}
</script>
<%--数据展示模板--%>
<script id="dev_hist_list_template" type="text/html">
    <ul class="title_ul">
        <li>月份</li>
        <li>摘要</li>
        <li>年份</li>
        <li>操作</li>
    </ul>
    {{each list as hist}}
    <ul class="list_goods_ul">
        <li>{{hist.article_title}}</li>
        <li style="overflow: hidden;">{{hist.article_desc}}</li>
        <li>{{hist.article_cid}}年</li>
        <li>
            <a href="#" onclick="article_click('{{hist.article_id}}');">
                <span class="glyphicon glyphicon-sunglasses" aria-hidden="true"> 查看</span>
            </a>
        </li>
    </ul>
    {{/each}}
    <!--分页-->
    <div id="page" class="page_div"></div>
</script>

<script>

    // 获取当前参数
    function getParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    /**
     * 获取年份
     */
    $.get('${ctx}/CategoryServlet?action=getAllCategory', function (data) {
        // console.log(data);
        var html = template('year_selected_template', {list: data});
        // document.getElementById('content').innerHTML = html;
        $('#year_selected').html(html);
    }, 'json');

    var cid = getParams('cid');
    if (cid != null) {
        // 获取搜索的关键字
        var keyWord = $('#input_search').val();
        getPageList(1, cid, keyWord)
    } else {
        // 获取搜索的关键字
        var keyWord = $('#input_search').val();
        getPageList(1, null, keyWord)
    }
    //$.get('${ctx}/ArticleServlet?action=getPageBean&currPage=1&pageSize=8'

    /**
     * 获取分页数据
     * @param currPage 当前页
     * @param cid 分类id
     * @param keyWord 关键字
     */
    function getPageList(currPage, cid, keyWord) {
        // console.log(keyWord);
        // console.log("currPage=" + currPage + ";cid=" + cid + ";keyWord=" + keyWord);
        $.post('${ctx}/ArticleServlet?action=getPageBean', {
            currPage: currPage,
            cid: cid,
            keyWord: keyWord
        }, function (data) {
            // console.log(data);
            var html = template('dev_hist_list_template', {list: data[0].list});
            // document.getElementById('content').innerHTML = html;
            $('#dev_hist_list').html(html);
            //分页
            $("#page").paging({
                pageNo: data[0].currentPage,
                totalPage: data[0].totalPage,
                totalSize: data[0].totalCount,
                callback: function (num) {
                    var cid = getParams('cid');
                    // 获取搜索的关键字
                    var keyWord = $('#input_search').val();
                    // console.log("num=" + num + ";cid=" + cid + ";keyWord=" + keyWord);
                    getPageList(num, cid, keyWord)
                }
            });
            // getPageList(data[0].currentPage, data[0].totalPage, );
        }, 'json')
    }

    /**
     * 搜索事件
     */
    $('#input_search_btn').click(function () {
        // 获取搜索的关键字
        var keyWord = $('#input_search').val();
        var cid = getParams('cid');
        if (cid != null) {
            // console.log("搜索中的cid:" + cid);
            getPageList(1, cid, keyWord)
        } else {
            getPageList(1, null, keyWord)
        }
    });

    /**
     * 年份点击
     * @param aid 年份
     */
    // function year_tag_click(cid) {
    //     console.log('年份 id:' + cid);
    //     // 获取搜索的关键字
    //     var keyWord = $('#input_search').val();
    //     getPageList(1, cid, keyWord);
    // };

    /**
     * 文章查看
     * @param aid 文章 id
     */
    function article_click(aid) {
        // console.log('文章 id', aid);
        $("#modal_view").fadeIn();
        $("#modal_content2").fadeIn();
        $.post('${ctx}/ArticleServlet?action=getArticleById', {aid: aid}, function (data) {
            // console.log(data);
            $('#article_content').html(data[0].article_content);
        }, 'json');
    };

    $("#close2").click(function () {
        $("#modal_view").fadeOut();
        $("#modal_content2").fadeOut();
    });
</script>

</body>
</html>
