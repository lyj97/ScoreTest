package com.servlet;

import Utils.GetMD5;
import Utils.TestCmd;
import com.uims.src.Model.UIMSTest;
import com.uims.src.Utils.HttpUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class IdentifyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isidentificated = false;
        String username = "";
        String password = "";
        boolean u_p_set = false;
        String message = "";

        String nickname = "";
        String studentID = "";
        String code = "";

        System.out.println("\n---------------UIMS Config---------------\n");
        SimpleDateFormat data_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat data = new SimpleDateFormat("yyyyMMdd");
        System.out.println("时间： \t" + data_time.format(new Date()));// new Date()为获取当前系统时间
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    //System.out.println(name + "=" + value);
                    if(name.contains("username")){
                        username = value;
                    }
                    else if(name.contains("password")){
                        password = value;
                    }
                }
                if(username != null && password != null && username.length() == 8 && password.length() > 0){
                    u_p_set = true;
                    System.out.println("usernsme:\t" + username);
                    break;
                }
            }
            if(u_p_set){
                HashMap<String, String> userInfo = null;
                try {
                    userInfo = UIMSTest.getUserInfo(username, password);
                    if(userInfo.containsKey("ERROR")){
                        message += "<b style=\"color:#ff4472\">教务认证失败</b><br/>请重试";
                        message += "<script language=\"javascript\">" +
                                "setTimeout(function(){" +
                                "      document.location = \"identify.jsp\";" +
                                "}, 3000);" +
                                "  </script>";
                    }
                    else{
                        nickname = userInfo.get("nickname");
                        studentID = userInfo.get("studentID");
                        code = GetMD5.getMD5Str("2045Print" + data.format(new Date()) + username);

                        System.out.println("认证码：\t" + code);

                        message += "认证通过<br/>" +
                                "<br/>欢迎您，<b>" + nickname + "</b>同学<br/><br/>" +
                                "欢迎使用北一2045小铺自助到店打印<br/>" +
                                "您的认证码如下<br/><b>" + code + "</b>";

                        message += "<script language=\"javascript\">" +
                                "setTimeout(function(){" +
                                "      document.location = \"identifySucceed.html?code=" + code +  "\";" +
                                "}, 2000);" +
                                "  </script>";
                    }
                }
                catch (Exception e){
                    //e.printStackTrace();
                    message += e.getMessage();
                    System.out.println("ERROR:\t" + e.getMessage());
                    message += "<script language=\"javascript\">" +
                            "setTimeout(function(){" +
                            "      document.location = \"identify.jsp\";" +
                            "}, 5000);" +
                            "  </script>";
                }
                finally {
                    HttpUtil.getHttpClient().close();
                }
            }
            else{
                message += "用户名或密码不符合条件<br/>请重试";
                message += "<script language=\"javascript\">" +
                        "setTimeout(function(){" +
                        "      document.location = \"identify.jsp\";" +
                        "}, 5000);" +
                        "  </script>";
                request.setAttribute("message",message);
                System.out.println("\n------------------Ending------------------\n");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }
        }catch (Exception e) {
            message= "认证失败！<br/>";
            message += e.getMessage();
            //e.printStackTrace();

        }
        request.setAttribute("message",message);
        System.out.println("\n------------------Ending------------------\n");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void go(HttpServletRequest request, HttpServletResponse response){

        String str = "<script language=\"javascript\">\n" +
                "      document.location = \"getFile3.php\";\n" +
                "  </script>";

        request.setAttribute("message",str);
        try {
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
