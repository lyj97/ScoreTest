<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/12
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=0.9, minimum-scale=0.9， user-scalable=1" />
    <title>自助授权</title>
    <style type="text/css">
        *{margin: 0;padding: 0;}
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: rgba(255, 255, 255, 0.57);
            border-radius: 18px;
            width: 415px;
            height: 306px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            /*opacity:0.85;*/
            -webkit-overflow-scrolling: touch;
        }
        .select{

            width: auto;
            padding: 0 1%;
            margin: 0;
            text-align: center; /*让div内部文字居中*/

        }
        #web_bg{
            position:fixed;
            top: 0;
            left: 0;
            width:100%;
            height:100%;
            /*max-width: 1000px;*/
            z-index:-10;
            zoom: 1;
            background-color: #fff;
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        }
    </style>
    <script src="changebg.js"></script>
    <script>
        function check_box() {
            var checkbox = document.getElementById("check");
            var submit = document.getElementById("submit");
            if(checkbox !== null && submit !== null){
                if(checkbox.checked){
                    checkbox.checked = "";
                    submit.disabled = "disabled";
                }
                else{
                    checkbox.checked = "checked";
                    submit.disabled = "";
                }
            }
        }
    </script>
</head>
<body onload="change_bg()">
<div class="main">
    <div class="wrapper">
        <!--背景图片-->
        <div id="web_bg" style="background-image: url(/img/bg/6.jpg);"></div>
        <!--其他代码 ... -->
    </div>
    <div style="text-align: center">
        <a href="zhifubao.html" style="color: red">到店使用支付宝红包码说明</a>
    </div>
    <div style="text-align: center">
        <a href="https://jq.qq.com/?_wv=1027&k=51b4SqX" style="color: #0da4ff; border-radius: 8px">加入北一2045小铺の群</a>
    </div>
    <form action="${pageContext.request.contextPath }/identify" enctype="multipart/form-data" method="post">

        <table border="0">

            <caption style="background-color:#EBC26A;  border-radius: 5px"><b>北一2045小铺自助授权系统</b></caption>

            <tr>
                <th style="width: 70px">教学号：</th>
                <td><input style="width: 315px" type="text" name="username" autocomplete="on" placeholder="请输入教学号"></td>
            </tr>
            <tr>
                <th>密码：</th>
                <td><input style="width: 315px" type="text" name="password" autocomplete="off" placeholder="请输入教务密码（默认密码为身份证号后六位）"></td>
            </tr>
            <tr style="align-items: center; text-align: center">
                <td style="border-radius: 5px" onclick="check_box()"><input style="border-radius: 5px" id="check" type="checkbox" onclick="check_box()"></td>
                <td style="text-align: center;  border-radius: 5px" onclick="check_box()">
                    <dov>
                        同意本网站使用您的教务账号，获取必要的信息以完成身份认证（点此同意）<br/>
                        <b style="color: #ff4472">【请确保合理使用，否则将被永久加入黑名单】</b>
                    </dov>
                </td>
            </tr>
            <tr style="align-items: center">
                <td style="align-items: center" colspan="2"><input disabled="disabled" id="submit" type="submit" border="0" value="提交" style="background-color:#79c0ff; border-radius: 8px; width: 413px; height: 40px"></td>
            </tr>
            <tr style="align-items: center">
                <td style="border-radius: 5px; align-items: center; text-align: center" colspan="2"><a href="index.jsp">返回首页</a> </td>
            </tr>
        </table>
    </form>
    <a id="down_bg" href="" style="color: #0da4ff;">下载背景图</a>
</div>
</body>
</html>
