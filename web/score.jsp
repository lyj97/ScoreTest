<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2019/1/17
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>查询结果</title>
    <style type="text/css">
        html, body {
            height: 100%;
        }
        body{
            position: relative;
        }
        /*遮罩层显示时body的样式*/
        .notScroll {
            overflow: hidden;
        }
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: #c3f7ff;
            border-radius: 20px;
            width: 400px;
            height: 200px;
            margin: auto;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            justify-items: center;
        }
        .black_overlay{
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.36);
            z-index:1001;
            -moz-opacity: 0.8;
            opacity:.80;
            filter: alpha(opacity=88);
        }
        .white_content {
            display: none;
            position: fixed;
            top: 15%;
            left: 15%;
            width: 55%;
            height: 55%;
            padding: 20px;
            border: 10px solid orange;
            background-color: white;
            z-index:1002;
            overflow: auto;
        }
    </style>
    <style type="text/css">

        table.hovertable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
            display: inline;
        }

        table.hovertable th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }

        table.hovertable tr {
            background-color:#d4e3e5;
        }

        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }

    </style>
    <script>
        function getPar(par){
            //获取当前URL
            var local_url = document.location.href;
            //获取要取得的get参数位置
            var get = local_url.indexOf(par +"=");
            if(get == -1){
                return false;
            }
            //截取字符串
            var get_par = local_url.slice(par.length + get + 1);
            //判断截取后的字符串是否还有其他get参数
            var nextPar = get_par.indexOf("&");
            if(nextPar != -1){
                get_par = get_par.slice(0, nextPar);
            }
            document.getElementById("page_count").innerHTML = "文件页数：" + get_par + "页。";
            document.getElementById("money").innerHTML = (get_par * 0.15).toFixed(2);
            return get_par;
        }
        function close_window() {
            var xx = navigator.userAgent.indexOf("Firefox") > -1 ?
                function () {
                    location.href = "about:blank";
                }
                :
                function () {
                    window.opener = null;
                    window.open("", "_self", "");
                    window.close();
                }
        }
    </script>
    <script src="js/jquery.tiaoxing.min.js" type="text/javascript"></script>
    <script src="js/progressBar.js"></script>
</head>
<body onload="getPar('page')" style="background: #c3f7ff">
<div class="main">
    <div style="margin:0 auto;">
        <table class="hovertable">
            <%--<tr>--%>
            <%--<th>Info Header 1</th><th>Info Header 2</th><th>Info Header 3</th>--%>
            <%--</tr>--%>
            <%--<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">--%>
            <%--<td>Item 1A</td><td>Item 1B</td><td>Item 1C</td>--%>
            <%--</tr>--%>





        </table>
        <table class="hovertable">
            <%--<tr>--%>
                <%--<th>Info Header 1</th><th>Info Header 2</th><th>Info Header 3</th>--%>
            <%--</tr>--%>
            <%--<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">--%>
                <%--<td>Item 1A</td><td>Item 1B</td><td>Item 1C</td>--%>
            <%--</tr>--%>

                ${Information }

                ${SaveTime }

                ${student }

                ${scoreStatistics }

                ${tableHeader }

                ${message }

        </table>

    </div>
    <div style="text-align: left"></div>
    <div id="fade" class="black_overlay"></div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/overhang.min.js"></script>
<script>
    var num = 0;
    var opened = 0;
    $(function(){
    })
    function openDialog(num){
        $('body,html').addClass('notScroll');
        opened = num;
        document.getElementById('score_' + num).style.display='block';
        document.getElementById('fade').style.display='block';
    }
    function closeDialog(){
        $('body,html').removeClass('notScroll');
        document.getElementById('score_' + opened).style.display='none';
        document.getElementById('fade').style.display='none';
    }
    function showInf() {
        if(num === 0) {
            $('body').overhang({
                custom: true,
                textColor: '#1f81fc',
                primary: '#aba0f0',
                accent: '#f5d6fc',
                duration: 5,
                message: '这个不是重修...T=T...'
            });
            num++;
        }
        else if(num === 1){
            $('body').overhang({
                custom: true,
                textColor: '#1f81fc',
                primary: '#f0d98b',
                accent: '#f5d6fc',
                duration: 5,
                message: '我错了...这个真不是重修...T=T...'
            });
            num++;
        }
        else if(num === 2){
            $('body').overhang({
                custom: true,
                textColor: '#1f81fc',
                primary: '#c692f0',
                accent: '#f5d6fc',
                duration: 5,
                message: '唉呀呀，别点了，好疼的...（T-T）'
            });
            num++;
        }
        else if(num >= 3){
            var msg = '呜呜呜';
            for(var i=0; i<num-2; i++){
                msg += msg;
            }
            $('body').overhang({
                custom: true,
                textColor: '#1f81fc',
                primary: '#f09074',
                accent: '#f5d6fc',
                duration: 5,
                message: msg + '...（T-T）'
            });
            num++;
        }
    }
</script>
</body>
</html>