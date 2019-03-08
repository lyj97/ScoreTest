package com.uims.src.Model;

import com.uims.src.Utils.Address;
import com.uims.src.Utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {

    protected static String md5char;
    protected static String username;
    protected static String password;

    protected static boolean success;
    protected static String student_id;
    protected static String term_id;
    protected static String loginName;
    protected static String nickname;

    protected static String JSON_id;

    protected static ArrayList<String> evalItemIds;

    /**
     *
     * login_header:
     * alu:教学号
     * jssionid:???
     * loginPage:userLogin.jsp
     * pwdStrength:1
     *
     * @return jssionid
     *
     *j_password	df6ed15de8e106a01c28a41b2954a4b6
     * j_username	54160907
     * mousePath	YHgABYAwB4YBgCIYCgCYYDgCpYEAC6YFADLYFQDbYFwDsYGgD8YHQENYIAEeYIQEvYJQE/YKAFgYKgFxYLQGDYMQGTYNAGkYOQG0YOgHFYPQHWYQAHmYRAH2YRwIIYSwIYYUAIpYVQI6YWgJLYXwJbYYwJrYaAJ9YaQKNYbAKeYdAKuYewK/YgQLPYhALgYiQLxYiwMCYjgMSYkwMjYmgM0YoQNEYqANVYsgNlYugN3YwQOHYyQOXYzwOoY0QO5Y2APKY4gPaY5wPqAbQFN
     */

    /**
     *
     * @param username 教学号
     * @param password 密码
     * @return
     */
    public static String get_uid(String username, String password){

        Login.username = username;
        Login.password = password;

        //System.out.println("get_uid");

        String md5chr = Utils.GetMD5.getMD5Str("UIMS" + username + password);

        //System.out.println(md5chr);

        Login.md5char = md5chr;
        Map<String, String> map = new HashMap();
        map.put("j_username",username);
        map.put("j_password",md5chr);
        map.put("mousePath","YHgABYAwB4YBgCIYCgCYYDgCpYEAC6YFADLYFQDbYFwDsYGgD8YHQENYIAEeYIQEvYJQE/YKAFgYKgFxYLQGDYMQGTYNAGkYOQG0YOgHFYPQHWYQAHmYRAH2YRwIIYSwIYYUAIpYVQI6YWgJLYXwJbYYwJrYaAJ9YaQKNYbAKeYdAKuYewK/YgQLPYhALgYiQLxYiwMCYjgMSYkwMjYmgM0YoQNEYqANVYsgNlYugN3YwQOHYyQOXYzwOoY0QO5Y2APKY4gPaY5wPqAbQFN");

        String jssionid = GetFirstPageCookie.getCookie();
        if(jssionid == null){
            return null;
        }

        Login.JSON_id = jssionid;

        String response = HttpUtil.post_login(Address.hostAddress + "/ntms/j_spring_security_check",username,jssionid,map);

        System.out.println(response);

        return response;

    }

    public static void main(String args[]){

        success = false;

        String user = "";
        String pass = "";

        if(args.length == 2){
            user = args[0];
            pass = args[1];
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.print("输入教学号：");
            user = sc.nextLine();
            System.out.print("输入密码：");
            pass = sc.nextLine();

//            user = "54160907";
//            pass = "225577";
//            System.out.println("login as 54160907");

        }

        HashMap hashMap = null;
        try {
            if(get_uid(user, pass) == null){
                return;
            }
            hashMap = HttpUtil.post_getcurrentuserinfo(Address.hostAddress + "/ntms/action/getCurrentUserInfo.do", user, JSON_id);
            student_id = (String) hashMap.get("student_id");
            term_id = (String) hashMap.get("term_id");
            loginName = (String) hashMap.get("login_name");
            nickname = (String) hashMap.get("nickname");
        }catch (Exception e){
            e.printStackTrace();
            success = false;
            return;
        }

        success = true;
        System.out.println(hashMap.toString());
        //System.out.println(get_uid(user,pass));

    }

    public static void res_1(){
        HttpUtil.post_res_1(Address.hostAddress + "/ntms/service/res.do",username,JSON_id,new Integer(term_id));
    }

    public static void getselectedcourse(){
//        res_1();
        HttpUtil.post_getselectedcourse(Address.hostAddress + "/ntms/service/res.do",username,JSON_id,new Integer(student_id),new Integer(term_id));
    }

    public static void getpingjiaoinf(){
//        res_1();
        evalItemIds = HttpUtil.post_pingjiao_information(Address.hostAddress + "/ntms/service/res.do",username,JSON_id);
    }

    public static void pingjiao(){
//        res_1();
        System.out.println("test 0");
        HttpUtil.post_pingjiao(Address.hostAddress + "/ntms/service/res.do",username,JSON_id, "5087812");
    }

    public static void pingjiao_tijiao(){
//        res_1();
        System.out.println("test 0");
        HttpUtil.post_pingjiao_tijiao(Address.hostAddress + "/ntms/action/eval/eval-with-answer.do",username,JSON_id, "5087812");
    }

    public static void onekey_pingjiao(){
//        res_1();
        for(int i=0; i<evalItemIds.size(); i++){
            HttpUtil.post_pingjiao(Address.hostAddress + "/ntms/service/res.do",username,JSON_id, evalItemIds.get(i));
            HttpUtil.post_pingjiao_tijiao(Address.hostAddress + "/ntms/action/eval/eval-with-answer.do",username,JSON_id, evalItemIds.get(i));
        }
    }

}
