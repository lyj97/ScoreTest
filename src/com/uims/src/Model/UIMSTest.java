package com.uims.src.Model;

import com.uims.src.Utils.HttpUtil;

import java.util.HashMap;
import java.util.Scanner;

public class UIMSTest {
    public static void main(String args[]){
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

        args = new String[2];
        args[0] = user;
        args[1] = pass;

        Login.main(args);
        Login.getselectedcourse();

    }

    public static HashMap<String, String> getUserInfo(String user, String pass){

        HttpUtil.creatNew();

        String[] args = new String[2];
        args[0] = user;
        args[1] = pass;
        Login.main(args);

        HashMap<String, String> userInfo = new HashMap();

        if(Login.success){
            userInfo.put("studentID",Login.student_id);
            userInfo.put("loginName",Login.username);
            userInfo.put("nickname",Login.nickname);
        }
        else{
            userInfo.put("ERROR","ERROR");
            System.out.println("Error!");
        }

        return userInfo;

    }

}
