package com.uims.src.Model;

import com.uims.src.Utils.Address;
import com.uims.src.Utils.HttpUtil;

public class GetFirstPageCookie {

    /**
     *
     * login_header:
     * alu:教学号
     * jssionid:???
     * loginPage:userLogin.jsp
     * pwdStrength:1
     *
     * @return jssionid
     */

    public static String getCookie(){

        System.out.println("getCookie");
        String res = "";
        try {
            res = HttpUtil.post_getHeader(Address.hostAddress + "/ntms/", "Set-Cookie");
        }
        catch (Exception e){
            System.out.println("ERROR IN getCookie():\t" + e.getMessage());
            return null;
        }
        System.out.println("Cooike:" + res);

        return res;
    }

    public static void main(String args[]){
        getCookie();
    }

}
