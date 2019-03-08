package com.servlet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.GetMD5;
import Utils.TestCmd;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;

public class UploadHandleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isidentificated = false;
        boolean is_order = false;
        System.out.println("\n---------------New Request---------------\n");
        SimpleDateFormat data_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat data = new SimpleDateFormat("yyyyMMdd");
        System.out.println("时间： \t" + data_time.format(new Date()));// new Date()为获取当前系统时间
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/files");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        String filename = "";
        String username = "";
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
                    System.out.println(name + "=" + value);
                    if(name.contains("username")){
                        username = value;
                    }
                    else if(name.contains("identifi")){
                        if(username != null && username.length() > 0) {
                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                            String code = GetMD5.getMD5Str("2045Print" + df.format(new Date()) + username);
                            System.out.println("认证码:\t" + code);
                            if(value.contains(code)){
                                isidentificated = true;
                            }
                        }
                        else{
                            System.out.println("Wrong username！");
                        }
                    }
                    else if(name.contains("type")){
                        if(value.contains("order")){
                            is_order = true;
                        }
                    }
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        message =  "<script language=\"javascript\">\n" +
                                "      document.location = \"index.jsp\";\n" +
                                "  </script>";
                        request.setAttribute("message",message);
                        System.out.println("\n------------------Ending------------------\n");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    filename = filename.replace(" ", "");

                    if(!(filename.endsWith(".pdf") || filename.endsWith(".PDF"))){
                        message = "<div style=\"color:red\">您上传的文件不是pdf文件<br/>上传失败！<br/></div>";
                    }
                    else {
                        //获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        //创建一个文件输出流
                        FileOutputStream out;
                        if(is_order) {
                            out = new FileOutputStream(savePath + "\\orders\\" + username + "_" + data.format(new Date()) + "_" + filename);
                        }
                        else{
                            out = new FileOutputStream(savePath + "\\" + username + "_" + filename);
                        }
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024 * 10];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除处理文件上传时生成的临时文件
                        item.delete();
                        message = "文件上传成功！<br>";
                    }
                    String temp_filename = "E:\\WebPrintTest\\out\\artifacts\\WebPrintTest_war_exploded\\WEB-INF\\files\\" + username + "_" + filename;
                    if(!filename.endsWith(".pdf")){
                        message += "<div style=\"color:red\">您上传的文件不是pdf文件<br/>打印失败！<br/></div>" +
                                "您现在可以<a href=\"javascript:window.opener=null;window.open('','_self');window.close();\">关闭此页面</a><br/>" +
                                "或<a href=\"index.jsp\">回到首页</a>。";
                    }
                    else {
                        if(!is_order) {
                            if (!isidentificated) {
                                message += "<br/><b style=\"color:red\">认证失败，未能开始打印！</b><br/>";
                            } else {

                                System.out.println("temp_filename:\t" + temp_filename);
                                int page = 0;
                                try {
                                    PDDocument document = PDDocument.load(new FileInputStream(temp_filename));
                                    page = document.getNumberOfPages();
                                    System.out.println("Pages:\t" + page);
                                    message += "<br/><div style=\"color:red\">文档页数：  " + page + "页<br/></div>";
                                    System.out.println("时间： \t" + data_time.format(new Date()));// new Date()为获取当前系统时间
                                    document.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    message += e.getMessage();
                                    System.out.println(e.getMessage());
                                }

                                //Print.main(temp_filename);
                                TestCmd.testWinCmd(temp_filename);
                                message += "" +
                                        "<script language=\"javascript\">\n" +
                                        "      document.location = \"succeed.html?page=" + page + "\";\n" +
                                        "  </script>";
                            }
                        }
                        else{
                            int page = 0;
                            try {
                                temp_filename = "E:\\WebPrintTest\\out\\artifacts\\WebPrintTest_war_exploded\\WEB-INF\\files\\orders\\" + username + "_" + data.format(new Date()) + "_" + filename;
                                System.out.println("temp_filename:\t" + temp_filename);
                                PDDocument document = PDDocument.load(new FileInputStream(temp_filename));
                                page = document.getNumberOfPages();
                                System.out.println("Pages:\t" + page);
                                message += "<br/><div style=\"color:red\">文档页数：  " + page + "页<br/></div>";
                                System.out.println("时间： \t" + data_time.format(new Date()));// new Date()为获取当前系统时间
                                document.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                                message += e.getMessage();
                                System.out.println(e.getMessage());
                            } finally {
                                message += "" +
                                        "<script language=\"javascript\">\n" +
                                        "      document.location = \"orderSucceed.html?page=" + page + "\";\n" +
                                        "  </script>";
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();

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