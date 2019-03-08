<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/1
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <title>欢迎光临北一2045小铺</title>
    <style type="text/css">
        *{margin: 0;padding: 0;}
        .main{
            text-align: center; /*让div内部文字居中*/
            background-color: rgba(255, 255, 255, 0.57);
            border-radius: 18px;
            width: 345px;
            height: 20px;
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
    </style>

</head>
<body>
<div class="main"  style="align-items: center">
    <div class="wrapper">
        <!--背景图片-->
        <div id="web_bg" style="background-image: url(mmexport1548851526806.jpg);"></div>
        <!--其他代码 ... -->
    </div>

    <a onclick="showInf()" style="color: #ffbb95; text-align: center">欢迎光临北一2045小铺</a>

</div>
<div class="bottom">
    2045.site <a href="http://www.miitbeian.gov.cn/" style="color: #4e5ead; text-align: center; float: bottom; left: 2px">吉ICP备19000593号</a>
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
            message: '祝：假期快乐！  小铺线上项目正在升级，敬请期待ヾ(≧▽≦*)o'
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

</script>
</body>
</html>
