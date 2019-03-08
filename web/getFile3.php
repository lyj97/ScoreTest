<?php
//获取当前文件所在的绝对目录
$dir =  dirname(__FILE__ . "/upload/files");
//扫描文件夹
$file = scandir($dir);
//显示
echo " <pre>";
print_r($file);

?>