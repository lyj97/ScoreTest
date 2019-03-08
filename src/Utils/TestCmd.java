package Utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: leizhimin
 * Date: 2008-7-18
 * Time: 14:18:27
 * Java调用Windows命令测试
 */
public class TestCmd implements Runnable {

    private static String filename = null;
    private static int number = 0;

    public static void main(String args[]) {
        testWinCmd(null);
        //dirOpt();
    }

    public static void testWinCmd(String filename) {
        System.out.println("------------------testWinCmd()--------------------");
        Runtime runtime = Runtime.getRuntime();
//        System.out.println(runtime.totalMemory());
//        System.out.println(runtime.freeMemory());
//        System.out.println(runtime.maxMemory());
//        System.out.println(runtime.availableProcessors());   //处理器数
        try {
            //执行一个exe文件
            //runtime.exec("notepad");
            System.out.println("Printing File: \t" + filename);
            runtime.exec("E:\\test\\print.exe \"" + filename + "\"");
            //执行批处理
            //runtime.exec("c:\\x.bat");
            //执行系统命令

            //process.waitFor();
            //process = runtime.exec("cmd /k java E:\\WebPrintTest\\src\\Utils\\Print test.txt ");
//            runtime.exec("cmd /c dir c:\\");

            //-------------- 文件操作 --------------

            //runtime.exec("cmd /c copy c:\\x.bat d:\\x.txt");   //copy并改名
            //runtime.exec("cmd /c rename d:\\x.txt x.txt.bak"); //重命名
            //runtime.exec("cmd /c move d:\\x.txt.bak c:\\");    //移动
            //runtime.exec("cmd /c del c:\\x.txt.bak");          //删除

            //-------------- 目录操作 --------------
            //runtime.exec("cmd /c md c:\\_test");          //删除
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testWinCmd(String filename, int number) {
        System.out.println("------------------testWinCmd()--------------------");
        Runtime runtime = Runtime.getRuntime();
        try {
            switch (number){
                case 0:{
                    return;
                }
                case 1:{
                    System.out.println("Printing File: \t" + filename);
                    //runtime.exec("E:\\test\\print.exe \"" + filename + "\"");
                }
                default:{
                    if(number < 0){
                        return;
                    }
                    else{
                        for(int i=0; i<number; i++){
                            System.out.println("Printing File: \t" + filename);
                            System.out.println("份数: \t 第\t" + (i + 1) + "份\t共\t" + number + "份");
                            //runtime.exec("E:\\test\\print.exe \"" + filename + "\"");

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行批处理文件，并获取输出流重新输出到控制台
     */
    public static void dirOpt() {
        System.out.println("------------------dirOpt()--------------------");
        Process process;
        try {
            //执行命令
            process = Runtime.getRuntime().exec("c:\\x.bat");
            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            //逐行读取输出到控制台
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for(int i=0; i<number; i++) {
            try {
                System.out.println("份数: \t 第\t" + (i + 1) + "份\t共\t" + number + "份");
                this.wait(3000);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }
}
