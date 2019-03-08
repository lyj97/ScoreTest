package Utils;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Print {
    public static void printTest(String filename)
    {
        FileInputStream fiStream = null;
        try {
            fiStream = new FileInputStream(filename);
        } catch (
                FileNotFoundException ffne) {
        }
        if (fiStream == null) {
            return;
        }

        //这是要打印文件的格式，如果是PDF文档要设为自动识别
        DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //2.得到要打印的文档类DOC
        Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
        //3.生成一个打印属性设置对象
        PrintRequestAttributeSet aset =
                new HashPrintRequestAttributeSet();
        //aset.add(new Copies(5));//Copies-打印份数5份
        aset.add(MediaSizeName.ISO_A4);//A4纸张
        //aset.add(Sides.DUPLEX);//双面打印
        //4.关键一步，得到当前机器上所有已经安装的打印机
        //传入的参数是文档格式跟打印属性，只有支持这个格式与属性的打印机才会被找到
        PrintService[] services = PrintServiceLookup.lookupPrintServices(fileFormat, null);
        if (services.length > 0) {
            //5.用打印服务生成一个文档打印任务，这里用的是第一个服务
            //也可以进行筛选，services[i].getName()可以得到打印机名称，可用名称进行比较得到自己想要的打印机
            for(int i=0; i<services.length; i++){
                System.out.println("service" + (i+1) + ":\t" + services[i].getName());
                if(services[i].getName().contains("L6170")){
                    DocPrintJob job = services[i].createPrintJob();
                    try{
                        job.print(myDoc, aset);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            System.out.println("length = 0!");
        }
    }

    public static void main(String args[]){
        if(args.length > 0) {
            for(int i=0; i<args.length; i++){
                if(args[i].endsWith(".pdf")){
                    printTest(args[i]);
                }
            }
        }
    }

}
