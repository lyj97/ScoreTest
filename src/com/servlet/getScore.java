package com.servlet;

import Utils.GetMD5;
import com.uims.src.Utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class getScore extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("GBK");   //设置从客户端接收到的字符的编码格式,并按照这种编码格式进行解码
//        response.setContentType("text/html;charset=utf-8");   //设置返回给客户端的字符的编码方式,通过http协议指定客户端按照这种编码方式进行解码转换
        System.out.println("\n---------------New Request---------------\n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("时间： \t" + sdf.format(new Date()));// new Date()为获取当前系统时间
        System.out.println("getRemortIP:\t" + getRemortIP(request));
        System.out.println("getIpAddr:\t" + getIpAddr(request));
        //消息提示
        String password = "";
        String username = "";
        String message = "";
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
                    if(name.contains("user")){
                        username = value;
                    }
                    else if(name.contains("pass")){
                        password = value;
                    }
                }
            }

            boolean pass_correct = false;
            if(username.length() == 8 && password.length()>0){
                JSONObject score = null;
                try {
                    score = HttpUtil.getScoreJSON("http://127.0.0.1:8001/query", username, password, false);
                }
                catch (Exception e){
                    score = HttpUtil.getScoreJSON("http://127.0.0.1:8001/query", username, password, true);
                    request.setAttribute("Information", "<tr>" +
                            "                <th colspan=\"5\" style=\"color: #ff667c\">" + "连接教务失败，过几分钟再试试可好咩(￣y▽,￣)╭ " +
                            "</th></tr>");
                }
                try{

                    JSONObject messageJSON = score.getJSONObject("message");

                    try {

                        String input_pass = GetMD5.getMD5Str("UIMS" + username + password);
                        String saved_pass = messageJSON.getString("password");

                        if(input_pass.equals(saved_pass)){
                            pass_correct = true;
                        }
                        else{
                            pass_correct = false;
                        }
                        System.out.println("pass_correct:\t" + pass_correct);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                        pass_correct = true;
                    }

                    if(pass_correct) {
                        System.out.println("res_pass_correct:\t" + pass_correct);
                        JSONArray scoreJSONArray = messageJSON.getJSONArray("value");
                        try {

                            JSONObject student = messageJSON.getJSONObject("student");

                            request.setAttribute("student", "<tr>" +
                                    "                <th>班级</th><th>" + student.getString("studNo") +
                                    "</th><th>姓名</th><th colspan=\"2\">" + student.getString("name") +
                                    "</th>" +
                                    "            </tr>");
                        } catch (Exception e) {

                            try{
                                JSONObject student = messageJSON.getJSONObject("student");

                                request.setAttribute("student", "<tr>" +
                                        "                <th>班级</th><th>" + student.getString("classNo") +
                                        "</th><th>姓名</th><th colspan=\"2\">" + student.getString("name") +
                                        "</th>" +
                                        "            </tr>");
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }

//                            e.printStackTrace();
                        }
                        try {

                            String saveTime = messageJSON.getString("SaveTime");

                            request.setAttribute("SaveTime", "<tr>" +
                                    "                <th>更新时间</th><th colspan=\"4\">" + saveTime +
                                    "</th></tr>");

                        }
                        catch (Exception e){
                            System.out.println("JSONError:\t" );
                            e.printStackTrace();
                        }
                        request.setAttribute("tableHeader", "<tr>" +
                                "                <th>科目</th><th>学期</th><th>重修</th><th>成绩</th><th>成绩分布</th>" +
                                "            </tr>");
                        try {
                            JSONObject Statistics = messageJSON.getJSONObject("scoreStatistics");
                            String scoreStatistics = "按首次成绩【绩点：" +  Statistics.getJSONArray("value").getJSONObject(0).getString("gpaFirst").substring(0,5) + " 成绩：" + Statistics.getJSONArray("value").getJSONObject(0).getString("avgScoreFirst").substring(0,5) + "】";
                            scoreStatistics += "\\n";
                            scoreStatistics += "按最好成绩【绩点：" +  Statistics.getJSONArray("value").getJSONObject(0).getString("gpaBest").substring(0,5) + " 成绩：" + Statistics.getJSONArray("value").getJSONObject(0).getString("avgScoreBest").substring(0,5) + "】";
                            request.setAttribute("scoreStatistics", "<th colspan=\"5\">" +
                                    "<input type=\"button\" value=\"学分绩点统计\" onclick=\"javascript:alert(\'" +
                                    scoreStatistics +
                                    "\')\">" +
                                    "</td></tr>");
                        }
                        catch (Exception e){
//                            e.printStackTrace();
                        }

                        boolean hasPercent = false;
                        String percent = "";
                        for (int i = 0; i < scoreJSONArray.size(); i++) {
                            try {
                                JSONArray percent_json = scoreJSONArray.getJSONObject(i).getJSONObject("percent").getJSONArray("items");
                                for (int j = 0; j < percent_json.size(); j++) {
                                    percent += percent_json.getJSONObject(j).getString("label");
                                    percent += " : ";
                                    percent += percent_json.getJSONObject(j).getString("percent").length() > 5 ? percent_json.getJSONObject(j).getString("percent").substring(0, 5) : percent_json.getJSONObject(j).getString("percent");
                                    percent += "% \\n ";
                                }
                                hasPercent = true;
                            } catch (Exception e) {
//                                e.printStackTrace();
                                percent = "暂时无法查询到成绩分布...";
                            }

                            message += "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\">" +
                                    "<td>" + scoreJSONArray.getJSONObject(i).getString("subject") +
                                    "</td><td>" + scoreJSONArray.getJSONObject(i).getString("term") +
                                    "</td><td>" + scoreJSONArray.getJSONObject(i).getString("relearn") +
                                    "</td><td>" + scoreJSONArray.getJSONObject(i).getString("score") +
                                    "</td><td><input ";
                            if(hasPercent) {
                                message += "type=\"button\" value=\"成绩分布\" onclick=\"javascript:alert(\'" +
                                        percent +
                                        "\')\">" +
                                        "</td></tr>";
                            }
                            else{
                                message += "disabled=\"disabled\" type=\"button\" value=\"暂无成绩分布\" onclick=\"javascript:alert(\'" +
                                        percent +
                                        "\')\">" +
                                        "</td></tr>";
                            }
                            hasPercent = false;
                            percent = "";
                        }
                        pass_correct = false;
                    }
                    else{
                        message= "查询失败，可能是<b style=\"color: red\">密码错误</b>！<br/>不如再试一次吧(●ˇ∀ˇ●)" +
                                "<script type=\"text/javascript\">" +
                                "setTimeout(window.location.href='query.jsp',2);" +
                                "</script> ";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    try{
                        message += score.getString("message") +
                                "<br/>当前信息刷新周期：" +
                                "<b style=\"color: red\"> 1 分钟</b>";
                    }
                    catch (Exception e1){
                        e1.printStackTrace();
                        message= "<b style=\"color: red\">查询失败！</b>";
//                        if(MyTime.isLate(new Date())){
//                            message= "<br/>很晚了，早些休息吧。<br/>愿君安<br/>φ(゜▽゜*)♪<br/>";
//                        }
                    }
                }
            }
            else{
                message = "查询失败！";
                System.out.println("\n------------------Ending------------------\n");
                request.getRequestDispatcher("/score.jsp").forward(request, response);
                return;
            }

        }catch (Exception e) {
            message = "查询失败！";
            e.printStackTrace();

        }
        request.setAttribute("message",message);
//        System.out.println("message:\n" + message);
        System.out.println("\n------------------Ending------------------\n");
        request.getRequestDispatcher("/score.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public String getRemortIP(HttpServletRequest request) {

        if (request.getHeader("x-forwarded-for") == null) {

            return request.getRemoteAddr();

        }

        return request.getHeader("x-forwarded-for");

    }

    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();

        }

        return ip;

    }

}
