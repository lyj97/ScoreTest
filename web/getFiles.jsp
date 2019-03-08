<%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 2018/12/1
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1， user-scalable=1" />
    <script type="text/javascript">
        function init(){
            var fso = new ActiveXObject("Scripting.FileSystemObject");
            // 获取文件相关信息
            var f1 = fso.GetFile("E:\\WebPrintTest\\out\\artifacts\\WebPrintTest_war_exploded\\WEB-INF\\upload");
            alert('文件上次修改日期:' + f1.DateLastModified);
            var drv;
            var s = '';
            // 获取磁盘相关信息
            drv = fso.GetDrive(fso.GetDriveName("C:\\"));
            s += 'Drive C:' + '-' + drv.VolumeName + '\n';
            s += 'Total Space:' + drv.TotalSize / 1024 + 'Kb' + '\n';
            s += 'Free Space:' + drv.FreeSpace / 1024 + 'Kb' + '\n';
            alert('C盘信息' + s);
            // 操作文件夹
            fldr = fso.GetFolder("F:\\test");
            alert('父文件夹名称：' + fldr + '\n');
            // 显示所在drive名称
            alert("Contained on drive " + fldr.Drive + "\n");
            // 判断是否为根目录
            if (fldr.IsRootFolder){
                alert("This is the root folder.");
            }
            else {
                alert("This folder isn't a root folder.");
            }
            // 创建新文件夹
            fso.CreateFolder ("F:\\test\\Bogus");
            alert("Created folder F:\\testBogus" + "\n");
            // 显示文件夹基础名称，不包含路径名
            alert("Basename = " + fso.GetBaseName("F:\\test\\bogus") + "\n");
            // 删除创建的文件夹
            fso.DeleteFolder ("F:\\test\\Bogus");
            alert("Deleted folder F:\\test\\Bogus" + "\n");
        }

        function init2(){
            var fso = new ActiveXObject("Scripting.FileSystemObject");
            // 获取目录下所有文件，对于该浏览器缓存目录，仅能获取到一个文件
            var path = '/upload/files';
            //path = 'F:\\test';
            var fldr = fso.GetFolder(path);
            var ff = new Enumerator(fldr.Files);
            var s = '';
            var fileArray = new Array();
            var fileName = '';
            var count = 0;
            for(; !ff.atEnd(); ff.moveNext()){
                fileName = ff.item().Name + '';
                fileName = fileName.toLowerCase();
                if(fileName.indexOf('cookie') >= 0){
                    fileName = fileName.substring(0,fileName.indexOf('.'));
                    fileName = fileName.substring(fileName.lastIndexOf('@')+1);
                    s += fileName + '\n';
                }
                count++;
            }
            alert(count + ',' + s);
        }
    </script>
</head>
<body onload="init2();">
</body>
</html>
