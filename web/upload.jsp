<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/1
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>文件上传</title>
    <style type="text/css">
        *{margin: 0;padding: 0;}
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: rgba(255, 255, 255, 0.57);
            border-radius: 18px;
            width: 345px;
            height: 285px;
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
    <script src="<%=request.getContextPath() %>/changetype.js"></script>
    <script src="changebg.js"></script>
    <script>
        function change() {
            document.getElementById("option_order").selected = "selected";
            change_bg();
        }
    </script>
</head>
<body onload="change()">
<div class="main" style="align-items: center">
    <div class="wrapper">
        <!--背景图片-->
        <div id="web_bg" style="background-image: url(/img/bg/6.jpg);"></div>
        <!--其他代码 ... -->
    </div>
    <div style="text-align: center">
        <a href="zhifubao.html" style="color: red">到店使用支付宝红包码说明</a>
    </div>
    <div style="text-align: center">
        <a href="arrivedemo.html" style="color: #ffb31c; text-align: left; float: left">到店打印演示</a><a href="https://jq.qq.com/?_wv=1027&k=51b4SqX" style="color: #0da4ff; text-align: right; float: right">加入北一2045小铺の群</a>
    </div>
    <form action="${pageContext.request.contextPath }/upload" enctype="multipart/form-data" method="post" style="align-items: center">

        <table border="0" style="text-align: center; align-items: center">

            <caption style="color:#3b60d1; background-color:#89d4ff; border-radius: 8px; font-size: 18px"><b>北一2045小铺自助打印系统</b></caption>

            <tr>
                <td colspan="2">请上传<a style="color: red"><b>PDF文件</b></a>，<a style="color: #0e93ff" href="到店自助打印流程介绍.docx">到店自助打印流程请点击下载</a></td>
            </tr>
            <tr>
                <th>用户名：</th>
                <td><input type="text" name="username" autocomplete="on" placeholder="请输入用户名"></td>
            </tr>
            <tr>
                <th>打印类型</th>
                <td>
                    <select name="type" id="type" onchange="return change_print_type(this.value)">
                        <option id="option_order" value="order" selected="selected">预约打印</option>
                        <option id="option_immediate" value="immediate">立即打印</option>
                    </select>
                </td>
            </tr>
            <tr id="location" style="display:">
                <th>领取地点</th>
                <td>
                    <select name="place" class="select">
                        <option value="jichuyuan">基础园食堂 (送达时间：次日20:00)</option>
                        <option value="shenziyuan">莘子园食堂 (送达时间：次日19:30)</option>
                        <option value="xinshitang">新食堂 (送达时间：次日19:00)</option>
                    </select>
                </td>
            </tr>
            <tr id="code" style="display:none">
                <th>授权码：</th>
                <td><input id="input_code" type="text" name="identification" autocomplete="off" placeholder="直接打印请输入授权码"></td>
            </tr>
            <tr>
                <th>上传文件:</th>
                <td><input type="file" name="file" accept=".pdf"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" border="0" value="提交" style="background-color:#e0dd69; width: 340px; height: 40px; border-radius: 10px; font-weight: bold"></td>
            </tr>
            <tr id="get_code" style="display: ">
                <td style="background-color:#89d4ff; color:#5094d4; text-align: center; border-radius: 8px" colspan="2"><a href="identify.jsp">通过UIMS教务系统自助获取授权码</a> </td>
            </tr>
        </table>
    </form>
    <a href="https://www.addpdf.cn/word-to-pdf" style="color: #ffb31c; text-align: left; float: left; left: 2px">友情链接：WORD转PDF</a>   <a id="down_bg" href="" style="color: #0da4ff; text-align: right; float: right; right: 2px">下载背景图</a>
</div>
</body>
</html>
