<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/1
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>提示</title>
    <style type="text/css">
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: #fff;
            border-radius: 20px;
            width: 400px;
            height: 350px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
    </style>
</head>
<body>
    <div class="main">
        ${message }
    </div>
</body>
</html>
