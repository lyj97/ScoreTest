package com.uims.src.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static CloseableHttpClient httpclient = null;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();//设置请求和传输超时时间
        httpclient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public static CloseableHttpClient getHttpClient(){
        return httpclient;
    }

    public static void creatNew(){
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();//设置请求和传输超时时间
        httpclient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            //e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * login_header:
     * alu:教学号
     * jssionid:???
     * loginPage:userLogin.jsp
     * pwdStrength:1
     *
     * @return uid
     */
    public static String post_login(String url, String alu, String jssionid, Map<String, String> map) {

        System.out.println("post_login");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("map:\t" + map.toString());

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        String cookie =  "loginPage=userLogin.jsp; alu=" + alu + "; pwdStrength=1; JSESSIONID=" + jssionid;
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        Header headers[] = response.getAllHeaders();


//        for(int i=0; i<headers.length; i++){
//            System.out.print(headers[i].getName() + "\t");
//            System.out.println(headers[i].getValue());
//        }

        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * searchterm_header:
     * Cookie:loginPage=userLogin.jsp; alu=教学号; JSESSIONID=？？？
     * Referer: http://uims.jlu.edu.cn/ntms/index.do
     * @return termid
     */
    public static HashMap post_getcurrentuserinfo(String url, String alu, String jssionid) {

        //System.out.println("post_getcurrentuserinfo");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        HttpPost httppost = new HttpPost(url);
        String cookie = "loginPage=userLogin.jsp; alu=" + alu + "; pwdStrength=1; JSESSIONID=" + jssionid;
        httppost.setHeader("Referer", Address.hostAddress + "/ntms/index.do");
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Cookie", cookie);
        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(), "UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }
        else{

            System.out.println("WRONG HTTP STATE:\t" + response.getStatusLine().getStatusCode());

            try{

                entity1 = response.getEntity();
                headers = response.getAllHeaders();

                // 得到httpResponse的实体数据
                if (entity1 != null) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(entity1.getContent(), "UTF-8"), 8 * 1024);
                        StringBuilder entityStringBuilder = new StringBuilder();
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            entityStringBuilder.append(line + "/n");
                        }
                        // 利用从HttpEntity中得到的String生成JsonObject
                        object = JSONObject.fromObject(entityStringBuilder.toString());
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                        return null;
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        String student_id = null;
        String term_id = null;
        String login_name = null;
        String nickname = null;

        try {

//            for (int i = 0; i < headers.length; i++) {
//                System.out.print(headers[i].getName() + "\t");
//                System.out.println(headers[i].getValue());
//            }

            if (object != null) {
                //System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
                return null;
            }

            login_name = (String) (object.get("loginName"));
            nickname = (String) (object.get("nickName"));

            JSONObject defRes = (JSONObject) object.get("defRes");
            student_id = defRes.getString("personId");
            term_id = defRes.getString("term_l");

            System.out.println("student_id:\t" + student_id);
            System.out.println("term_id:\t" + term_id);
            System.out.println("login_name:\t" + login_name);
            System.out.println("nickname:\t" + nickname);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        HashMap<String, String> student_info = new HashMap<>();
        student_info.put("student_id", student_id);
        student_info.put("term_id", term_id);
        student_info.put("login_name", login_name);
        student_info.put("nickname", nickname);
        return student_info;
    }

    /**
     * branch: "byId"
     * params: {termId: ????}
     * termId: ???
     * tag: "search@teachingTerm"
     */
    public static HashMap post_res_1(String url, String alu, String jssionid, int term_id) {

        System.out.println("post_res_1");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("term_id:\t" + term_id);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        JSONObject request_json1 = new JSONObject();
        request_json1.put("termId",term_id);

        JSONObject request_json = new JSONObject();
        request_json.put("tag","search@teachingTerm");
        request_json.put("branch","byId");
        request_json.put("params",request_json1);

        StringEntity json_entity = null;

        try {
            json_entity = new StringEntity(request_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpPost httppost = new HttpPost(url);
        String cookie = "alu=" + alu + ";loginPage=userLogin.jsp;JSESSIONID=" + jssionid;
        httppost.setHeader("Referer",Address.hostAddress + "/ntms/index.do");
        httppost.setHeader("Connection","keep-alive");
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
//        httppost.removeHeader(httppost.getHeaders("Content-Type")[0]);
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(json_entity);

//        Header[] httpHeaders = httppost.getAllHeaders();
//        System.out.println("HEADERS");
//        for(int i=0; i<httpHeaders.length; i++){
//            System.out.println(httpHeaders[i].getName() + "\t" + httpHeaders[i].getValue());
//        }
//        System.out.println("ENTITY");
//        try {
//            System.out.println(EntityUtils.toString(json_entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String result = null;
        HashMap<Integer, String> courses = new HashMap();

        try {

//            for (int i = 0; i < headers.length; i++) {
//                System.out.print(headers[i].getName() + "\t");
//                System.out.println(headers[i].getValue());
//            }

            if (object != null) {
                //System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
                return null;
            }

            JSONArray value;
            JSONObject course = null;
            JSONObject course_inf = null;

            try{
                for(int i=0; ; i++){
                    value = (JSONArray)(object.get("value"));
                    for(int j=0 ; ; j++) {
                        course = (JSONObject) value.get(j);
                        course_inf = (JSONObject) course.get("teachClassMaster");
                        course_inf = (JSONObject) course_inf.get("lessonSegment");
                        courses.put(j, (String) course_inf.get("fullName"));
                    }
                }
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

//            for( Map.Entry<Integer, String> entry : courses.entrySet()){
//                System.out.println(entry.getKey() + "\t" + entry.getValue());
//            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     *
     * searchterm_header:
     * Cookie:loginPage=userLogin.jsp; alu=教学号; JSESSIONID=？？？
     * Referer: http://uims.jlu.edu.cn/ntms/index.do
     * @return termid
     *
     * 参数
     * branch	default
     * params	{…}
     *     studId	???
     *     termId	???
     * tag	teachClassStud@schedule
     *
     * {"tag":"teachClassStud@schedule","branch":"default","params":{"termId":135,"studId":266662}}
     */
    public static HashMap post_getselectedcourse(String url, String alu, String jssionid, int student_id, int term_id) {

        //System.out.println("post_getselectedcourse");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("student_id:\t" + student_id);
//        System.out.println("term_id:\t" + term_id);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        JSONObject request_json1 = new JSONObject();
        request_json1.put("termId",term_id);
        request_json1.put("studId",student_id);

        JSONObject request_json = new JSONObject();
        request_json.put("tag","teachClassStud@schedule");
        request_json.put("branch","default");
        request_json.put("params",request_json1);

        StringEntity json_entity = null;

        try {
            json_entity = new StringEntity(request_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpPost httppost = new HttpPost(url);
        String cookie =  "loginPage=userLogin.jsp;alu=" + alu + ";JSESSIONID=" + jssionid;
        httppost.setHeader("Referer",Address.hostAddress + "/ntms/index.do");
        httppost.setHeader("Host",Address.host);
        httppost.setHeader("Origin",Address.hostAddress);
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(json_entity);

//        Header[] httpHeaders = httppost.getAllHeaders();
//        System.out.println("HEADERS");
//        for(int i=0; i<httpHeaders.length; i++){
//            System.out.println(httpHeaders[i].getName() + "\t" + httpHeaders[i].getValue());
//        }
//        System.out.println("ENTITY");
//        try {
//            System.out.println(EntityUtils.toString(json_entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String result = null;
        HashMap<Integer, String> courses = new HashMap();

        try {

//            for (int i = 0; i < headers.length; i++) {
//                System.out.print(headers[i].getName() + "\t");
//                System.out.println(headers[i].getValue());
//            }

            if (object != null) {
                //System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
                return null;
            }

            JSONArray value = (JSONArray)object.get("value");
            JSONObject course = null;
            JSONObject course_inf = null;
            try{
                for(int i=0; ; i++){
                    course = (JSONObject) value.get(i);
                    course_inf = (JSONObject) course.get("teachClassMaster");
                    course_inf = (JSONObject) course_inf.get("lessonSegment");
                    courses.put(i,(String) course_inf.get("fullName"));
                }
            }
            catch (Exception e1){
//                e1.printStackTrace();
            }

//            for( Map.Entry<Integer, String> entry : courses.entrySet()){
//                System.out.println(entry.getKey() + "\t" + entry.getValue());
//            }

        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        return courses;
    }

    /**
     *
     * searchterm_header:
     * Cookie:loginPage=userLogin.jsp; alu=教学号; JSESSIONID=？？？
     * Referer: http://uims.jlu.edu.cn/ntms/index.do
     * @return termid
     *
     * 参数
     * branch	self
     * params	{…}
     *      blank	Y
     * tag	student@evalItem
     *
     * {"tag":"teachClassStud@schedule","branch":"default","params":{"termId":135,"studId":266662}}
     */
    public static ArrayList<String> post_pingjiao_information(String url, String alu, String jssionid) {

        System.out.println("post_pingjiao_information");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("student_id:\t" + student_id);
//        System.out.println("term_id:\t" + term_id);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        JSONObject request_json1 = new JSONObject();
        request_json1.put("blank","Y");

        JSONObject request_json = new JSONObject();
        request_json.put("tag","student@evalItem");
        request_json.put("branch","self");
        request_json.put("params",request_json1);

        StringEntity json_entity = null;

        try {
            json_entity = new StringEntity(request_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpPost httppost = new HttpPost(url);
        String cookie =  "loginPage=userLogin.jsp;alu=" + alu + ";JSESSIONID=" + jssionid;
        httppost.setHeader("Referer",Address.hostAddress + "/ntms/index.do");
        httppost.setHeader("Host",Address.host);
        httppost.setHeader("Origin",Address.hostAddress);
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(json_entity);

//        Header[] httpHeaders = httppost.getAllHeaders();
//        System.out.println("HEADERS");
//        for(int i=0; i<httpHeaders.length; i++){
//            System.out.println(httpHeaders[i].getName() + "\t" + httpHeaders[i].getValue());
//        }
//        System.out.println("ENTITY");
//        try {
//            System.out.println(EntityUtils.toString(json_entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String result = null;

        ArrayList<String> evalItemIds = new ArrayList<>();

        try {

            for (int i = 0; i < headers.length; i++) {
                System.out.print(headers[i].getName() + "\t");
                System.out.println(headers[i].getValue());
            }

            if (object != null) {
                System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
            }

            JSONArray value = (JSONArray)object.get("value");
            JSONObject course = null;
            JSONObject course_inf = null;

            try{
                for(int i=0; ; i++){
                    course = (JSONObject) value.get(i);
                    course_inf = (JSONObject) course.get("target");
                    evalItemIds.add((String) course.get("evalItemId"));

                    System.out.println("NUMBER" + (i+1));
                    System.out.println("id;\t" + course.get("evalItemId"));
                    System.out.println("name;\t" + course_inf.get("name"));

                    course_inf = (JSONObject) course.get("targetClar");

                    System.out.println("course;\t" + course_inf.get("notes") + "\n");

                }
            }
            catch (Exception e1){
//                e1.printStackTrace();
            }

            System.out.println(evalItemIds);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return evalItemIds;
    }

    /**
     *
     * searchterm_header:
     * Cookie:loginPage=userLogin.jsp; alu=教学号; JSESSIONID=？？？
     * Referer: http://uims.jlu.edu.cn/ntms/page/eval/eval_detail_120.html?eitem=？？？
     * @return termid
     *
     * 参数
     * branch	self
     * params	{…}
     *      blank	Y
     * tag	student@evalItem
     *
     * {"tag":"teachClassStud@schedule","branch":"default","params":{"termId":135,"studId":266662}}
     */
    public static boolean post_pingjiao(String url, String alu, String jssionid, String evalItemId) {

        System.out.println("post_pingjiao");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("student_id:\t" + student_id);
//        System.out.println("term_id:\t" + term_id);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        JSONObject request_json1 = new JSONObject();
        request_json1.put("evalItemId", evalItemId);

        JSONObject request_json = new JSONObject();
        request_json.put("tag","get@EvalItem");
        request_json.put("params",request_json1);

        StringEntity json_entity = null;

        try {
            json_entity = new StringEntity(request_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpPost httppost = new HttpPost(url);
        String cookie =  "loginPage=userLogin.jsp;alu=" + alu + ";JSESSIONID=" + jssionid;
        httppost.setHeader("Referer",Address.hostAddress + "/ntms/page/eval/eval_detail_120.html?eitem=" + evalItemId);
        httppost.setHeader("Host",Address.host);
        httppost.setHeader("Origin",Address.hostAddress);
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(json_entity);

//        Header[] httpHeaders = httppost.getAllHeaders();
//        System.out.println("HEADERS");
//        for(int i=0; i<httpHeaders.length; i++){
//            System.out.println(httpHeaders[i].getName() + "\t" + httpHeaders[i].getValue());
//        }
//        System.out.println("ENTITY");
//        try {
//            System.out.println(EntityUtils.toString(json_entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String result = null;

        //ArrayList<String> evalItemIds = new ArrayList<>();

        try {

            for (int i = 0; i < headers.length; i++) {
                System.out.print(headers[i].getName() + "\t");
                System.out.println(headers[i].getValue());
            }

            if (object != null) {
                System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
            }

            JSONObject value = (JSONObject)object.get("value");
            JSONObject course = null;
            JSONObject person_inf = null;

            try {
                course = (JSONObject) value.get("targetClar");
                person_inf = (JSONObject) course.get("person");

                System.out.println("INF");
                System.out.println("course;\t" + course.get("notes"));
                System.out.println("name;\t" + person_inf.get("name") + "\n");
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

//            System.out.println(evalItemIds);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     *
     * searchterm_header:
     * Cookie:loginPage=userLogin.jsp; alu=教学号; JSESSIONID=？？？
     * Referer: http://uims.jlu.edu.cn/ntms/page/eval/eval_detail_120.html?eitem=？？？
     * @return termid
     *
     * 参数
     * {
     *      "guidelineId":120,
     *      "evalItemId":"5086141",
     *      "answers":{
     *          "prob11":"A",
     *          "prob12":"A",
     *          "prob13":"N","
     *          prob14":"A",
     *          "prob15":"A",
     *          "prob21":"A",
     *          "prob22":"A",
     *          "prob23":"A",
     *          "prob31":"A",
     *          "prob32":"A",
     *          "prob33":"A",
     *          "prob41":"A",
     *          "prob42":"A",
     *          "prob43":"A",
     *          "prob51":"A",
     *          "prob52":"A",
     *          "sat6":"A",
     *          "mulsel71":"K",
     *          "advice72":"",
     *          "prob73":"Y"
     *      },
     *      "clicks":{
     *          "_boot_":0,
     *          "prob11":940480,
     *          "prob12":941785,
     *          "prob13":943263,
     *          "prob14":944599,
     *          "prob15":946087,
     *          "prob21":951274,
     *          "prob22":953240,
     *          "prob23":954562,
     *          "prob31":956457,
     *          "prob32":957899,
     *          "prob33":959297,
     *          "prob41":961440,
     *          "prob42":963751,
     *          "prob43":965425,
     *          "prob51":968037,
     *          "prob52":969919,
     *          "sat6":972365,
     *          "mulsel71":973853,
     *          "prob73":975496
     *      }
     *  }
     */
    public static boolean post_pingjiao_tijiao(String url, String alu, String jssionid, String evalItemId) {

        System.out.println("post_pingjiao_tijiao");

//        System.out.println("url:\t" + url);
//        System.out.println("alu:\t" + alu);
//        System.out.println("jssionid:\t" + jssionid);
//        System.out.println("student_id:\t" + student_id);
//        System.out.println("term_id:\t" + term_id);

        HashMap<String, String> map = new HashMap();


//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        JSONObject j_evalItemId = new JSONObject();
        j_evalItemId.put("evalItemId", evalItemId);

        JSONObject j_guidelineId = new JSONObject();
        j_guidelineId.put("guidelineId", 120);

        JSONObject j_clicks = new JSONObject();
        j_clicks.put("_boot_", 0);
        j_clicks.put("prob11", 940480);
        j_clicks.put("prob12", 941785);
        j_clicks.put("prob13", 943263);
        j_clicks.put("prob14", 944599);
        j_clicks.put("prob15", 946087);
        j_clicks.put("prob21", 951274);
        j_clicks.put("prob22", 953240);
        j_clicks.put("prob23", 954562);
        j_clicks.put("prob31", 956457);
        j_clicks.put("prob32", 957899);
        j_clicks.put("prob33", 959297);
        j_clicks.put("prob41", 961440);
        j_clicks.put("prob42", 963751);
        j_clicks.put("prob43", 965425);
        j_clicks.put("prob51", 968037);
        j_clicks.put("prob52", 969919);
        j_clicks.put("sat6", 972365);
        j_clicks.put("mulsel71", 973853);
        j_clicks.put("prob73", 975496);


        JSONObject j_answers = new JSONObject();
        j_answers.put("advice72", "");
        j_answers.put("mulsel71", "K");
        j_answers.put("prob11", "A");
        j_answers.put("prob12", "A");
        j_answers.put("prob13", "N");
        j_answers.put("prob14", "A");
        j_answers.put("prob15", "A");
        j_answers.put("prob21", "A");
        j_answers.put("prob22", "A");
        j_answers.put("prob23", "A");
        j_answers.put("prob31", "A");
        j_answers.put("prob32", "A");
        j_answers.put("prob33", "A");
        j_answers.put("prob41", "A");
        j_answers.put("prob42", "A");
        j_answers.put("prob43", "A");
        j_answers.put("prob51", "A");
        j_answers.put("prob52", "A");
        j_answers.put("prob73", "Y");
        j_answers.put("sat6", "A");

        JSONObject request_json = new JSONObject();
        request_json.put("evalItemId",evalItemId);
        request_json.put("guidelineId",120);
        request_json.put("clicks",j_clicks);
        request_json.put("answers",j_answers);

        StringEntity json_entity = null;

        try {
            json_entity = new StringEntity(request_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpPost httppost = new HttpPost(url);
        String cookie =  "loginPage=userLogin.jsp;alu=" + alu + ";JSESSIONID=" + jssionid;
        httppost.setHeader("Referer",Address.hostAddress + "/ntms/page/eval/eval_detail_120.html?eitem=" + evalItemId);
        httppost.setHeader("Host",Address.host);
        httppost.setHeader("Origin",Address.hostAddress);
        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Cookie",cookie);
        httppost.setEntity(json_entity);

//        Header[] httpHeaders = httppost.getAllHeaders();
//        System.out.println("HEADERS");
//        for(int i=0; i<httpHeaders.length; i++){
//            System.out.println(httpHeaders[i].getName() + "\t" + httpHeaders[i].getValue());
//        }
//        System.out.println("ENTITY");
//        try {
//            System.out.println(EntityUtils.toString(json_entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CloseableHttpResponse response = null;
        JSONObject object = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity1 = null;
        Header headers[] = null;

        if (response.getStatusLine().getStatusCode() == 200) {

            entity1 = response.getEntity();
            headers = response.getAllHeaders();

            // 得到httpResponse的实体数据
            if (entity1 != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity1.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    object = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String result = null;

        //ArrayList<String> evalItemIds = new ArrayList<>();

        try {

            for (int i = 0; i < headers.length; i++) {
                System.out.print(headers[i].getName() + "\t");
                System.out.println(headers[i].getValue());
            }

            if (object != null) {
                System.out.println(object.toString());
            } else {
                System.out.println("object IS NULL!");
            }

            JSONObject value = (JSONObject)object;

            try {
                System.out.println("INF");
                System.out.println("count:\t" + value.get("count"));
                System.out.println("errno:\t" + value.get("errno"));
                System.out.println("msg:\t" + value.get("msg"));
                System.out.println("status:\t" + value.get("status") + "\n");
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

            if(value.get("status").equals(0)){
                System.out.println("评教成功！\n");
            }

//            System.out.println(evalItemIds);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String post_getHeader(String url, String key) {

        //System.out.println("post_getHeader");

//        System.out.println("url:\t" + url);
//        System.out.println("key:\t" + key);

        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        Header headers[] =  response.getHeaders(key);
        if(headers.length == 1){
            try {
                String str = headers[0].getValue();
                str = str.split(";")[0];
                return str.split("=")[1];
            }
            catch (Exception e){
                //e.printStackTrace();
                return "ERROR!";
            }
        }

        else{
            return "ERROR: More then one! length: " + headers.length;
        }
    }

    public static JSONObject getScoreJSON(String url, String user, String pass, boolean isSaved){
        url += isSaved ? ("?t=savedScore&u=" + user + "&p=" + pass) : ("?t=score&u=" + user + "&p=" + pass);
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e1) {
            //e1.printStackTrace();
        }
        JSONObject resultJSON = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity.getContent(),"UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    // 利用从HttpEntity中得到的String生成JsonObject
                    resultJSON = JSONObject.fromObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return resultJSON;
    }

    public static JSONObject getJSONObjectByPost(HttpPost httpPost) {
        JSONObject resultJsonObject = null;
        //List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
        // 将传过来的参数填充到List<NameValuePair>中
//        if (paramsHashMap != null && !paramsHashMap.isEmpty()) {
//            for (Map.Entry<String, String> entry : paramsHashMap.entrySet()) {
//                nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//        }

        //UrlEncodedFormEntity entity = null;
        try {
            // 利用List<NameValuePair>生成Post请求的实体数据
            // 此处使用了UrlEncodedFormEntity!!!
            //entity = new UrlEncodedFormEntity(nameValuePairArrayList, "utf-8");
            // 为HttpPost设置实体数据
            //httpPost.setEntity(entity);
            // HttpClient发出Post请求
            HttpResponse httpResponse = httpclient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 得到httpResponse的实体数据
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
                        StringBuilder entityStringBuilder = new StringBuilder();
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            entityStringBuilder.append(line + "/n");
                        }
                        // 利用从HttpEntity中得到的String生成JsonObject
                        resultJsonObject = JSONObject.fromObject(entityStringBuilder.toString());
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return resultJsonObject;
    }

}