<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/1
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>欢迎光临北一2045小铺</title>
    <link rel="stylesheet" href="css/style.css">
    <style type="text/css">
      *{margin: 0;padding: 0;}
      .main{
        text-align: center; /*让div内部文字居中*/
        background-color: rgba(255, 255, 255, 0.57);
        border-radius: 18px;
        width: 345px;
        height: 145px;
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
    </style>
    <script>

      function random_color(){
        return '#'+Math.floor(Math.random()*0xffffff).toString(16);
      }
      function change_bg() {
        document.getElementById("bt1").style.backgroundColor = random_color();
        document.getElementById("bt2").style.backgroundColor = random_color();
      }

    </script>
  </head>
  <body onload="change_bg()">
    <div class="main">
      <table border="0" style="text-align: center; align-items: center">

        <caption onclick="showInf()" style="color:#3b60d1; background-color:#89d4ff; border-radius: 10px; font-size: 18px"><b>欢迎光临北一2045小铺</b></caption>

        <tr>&ensp;</tr>
        <tr>
          <th><a href="upload.jsp"><input id="bt1" type="button" value="自助打印" style="width: 340px; height: 40px; border-radius: 10px; font-weight: bold"/></a></th>
        </tr>

        <tr>
          <th><a href="query.jsp"><input id="bt2" type="button" value="成绩查询" style="width: 340px; height: 40px; border-radius: 10px; font-weight: bold"/></a></th>
        </tr>


      </table>


    </div>
    <div class="monster">
      <div class="eye">
        <div class="eyeball"></div>
      </div>
      <div class="mouth"></div>
    </div>
    <div class="monster blue">
      <div class="eye">
        <div class="eyeball"></div>
      </div>
      <div class="mouth"></div>
    </div>
    <div class="pageLoading">
      <div class="monster">
        <div class="eye">
          <div class="eyeball"></div>
        </div>
        <div class="mouth"></div>
      </div>
      <div class="loading">
        <div class="bar"></div>
      </div>
    </div>
    <script>
      // document.location = "query.jsp";
      function showInf() {
        $('body').overhang({
          custom: true,
          textColor: '#1f81fc',
          primary: '#aba0f0',
          accent: '#f5d6fc',
          duration: 5,
          message: '北一2045小铺位于吉林大学前卫南区北苑一公寓.小铺提供打印、复印服务，价格合理；小铺拥有线上自助打印系统，服务方便快捷，欢迎体验。'
        });
      }
    </script>
    <script src="js/index.js"></script>
  </body>
</html>
