<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>成绩查询</title>
    <style type="text/css">
        *{margin: 0;padding: 0;}
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: rgba(255, 255, 255, 0.57);
            border-radius: 18px;
            width: 350px;
            height: 185px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            /*opacity:0.85;*/
            /*overflow: auto;*/
            -webkit-overflow-scrolling: touch;
        }
        .bottom{
            text-align: center; /*让div内部文字居中*/
            background-color: rgba(84, 173, 255, 0.57);
            border-radius: 18px;
            margin: auto;
            position: fixed;
            left: 0;
            right: 0;
            bottom: 0;
            /*opacity:0.85;*/
            /*overflow: auto;*/
            -webkit-overflow-scrolling: touch;
        }
        #web_bg{
            position:fixed;
            top: 0;
            left: 0;
            width:100%;
            height:100%;
            min-width: 350px;
            z-index:-10;
            zoom: 1;
            background-color: #fff;
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        }
        /*div {*/
            /*width:800px;*/
            /*margin:200px auto;*/
            /*border:1px solid red;*/
            /*text-align:center;*/
        /*}*/
        input {
            width:100%;
            height:30px;
            border:1px solid #dbdbdb;
            outline:none;
            font-size:15px;
            text-indent:5px;
        }
        label {
            position:relative;
            width:343px;
            margin:5px auto;
            display:inline-block;
        }
        label:after {
            content:"";
            display:inline-block;
            width:0;
            height:2px;
            background: #46ffc1;
            transition:width 1s;
            position:absolute;
            bottom:1px;
            left:1px;
            right: 1px;
        }
        .active:after {
            width:calc(100% - 2px)
        }
    </style>
    <script src="changebg.js"></script>
</head>
<body onload="change_bg()">
<div class="main"  style="align-items: center">
    <div class="wrapper">
        <!--背景图片-->
        <div id="web_bg" style="background-image: url(/img/bg/6.jpg);"></div>
        <!--其他代码 ... -->
    </div>
    <form action="${pageContext.request.contextPath }/score" enctype="multipart/form-data" method="post" onsubmit="return notify()">

        <table border="0" style="text-align: center; align-items: center">

            <caption onclick="showInf()" style="color:#3b60d1; background-color:#89d4ff; width: 343px; border-radius: 10px; font-size: 18px"><b>北一2045小铺服务 — 成绩查询</b></caption>

            <tr>
                <%--<th>教学号：</th>--%>
                <td>
                    <label><input id="user" type="text" name="username" autocomplete="on" placeholder="教学号"></label>
                </td>
            </tr>

            <tr>
                <%--<th>教务密码：</th>--%>
                <td>
                    <label><input id="pass" type="password" name="password" autocomplete="off" placeholder="密码（默认为【身份证号后6位】）"></label>
                </td>
            </tr>

            <tr></tr>

            <tr>
                <td><input type="submit" border="0" value="查询" style="background-color:#0ce0e0; width: 340px; height: 40px; border-radius: 10px; font-weight: bold"></td>
            </tr>

        </table>
    </form>
    <a href="https://jq.qq.com/?_wv=1027&k=51b4SqX" style="color: #ffbb95; text-align: left; float: left; left: 2px">加入北一2045小铺の群</a><a id="down_bg" href="" style="color: #0da4ff; text-align: right;  float: right; right: 2px">下载背景图</a>

</div>
<div class="bottom">
    2045.site <a href="http://www.miitbeian.gov.cn/" style="color: #000000; text-align: center; float: bottom; left: 2px">吉ICP备19000593号</a>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/overhang.min.js"></script>
<script>

    setTimeout(function () {
        $('body').overhang({
            custom: true,
            textColor: '#fc204a',
            primary: '#e2c4f0',
            accent: '#8edffc',
            duration: 5,
            message: '成绩查询（实时刷新）测试中，欢迎体验 o((⊙﹏⊙))o '
        });
    }, 500);

    function showInf() {
        $('body').overhang({
            custom: true,
            textColor: '#1f81fc',
            primary: '#aba0f0',
            accent: '#f5d6fc',
            duration: 5,
            message: '北一2045小铺位于吉林大学前卫南区北苑一公寓。小铺提供打印、复印服务，价格合理；小铺拥有线上自助打印系统，服务方便快捷，欢迎体验。'
        });
    }

    function notify() {

        var user = document.getElementById("user");
        var pass = document.getElementById("pass");

        if((user.value.length < 8) || !(pass.value.length > 0)) {
            $('body').overhang({
                custom: true,
                textColor: '#fc3f5f',
                primary: '#198cf0',
                accent: '#8edffc',
                message: '请输入正确的用户名和密码'
            });
            return false;
        }

        // else {
        //     $('body').overhang({
        //         custom: true,
        //         textColor: '#eadbfc',
        //         primary: '#f03a2c',
        //         accent: '#8edffc',
        //         duration: 8,
        //         message: '欢迎光临北一小铺。小铺现封存日志，排查故意扰乱系统之来客。小铺不会公布其个人信息，但将永久禁止其使用本系统。在此期间，小铺服务暂停。有需要者，可联系QQ 1159386449 获取帮助。'
        //     });
        // }

    return true;

    }

    $("input").focus(function() {
        $(this).parent("label").addClass("active");
    });
    $("input").blur(function() {
        if ($(this).val() == "") {
            $(this).parent("label").removeClass("active");
        }
    })

</script>
</body>
</html>
