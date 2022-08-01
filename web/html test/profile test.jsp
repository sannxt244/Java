<%-- 
    Document   : profile test
    Created on : Jul 15, 2022, 8:47:51 AM
    Author     : Minh Lee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <input type="file" id="i_file" value="" onchange="upload(this.value)"> 
<input type="button" id="i_submit" value="Submit">
<br>
<img src="..\pictures\824540.jpg" alt="yolo" width="200"  />
<br>
<div id="disp_tmp_path"></div>

<script>
    function upload(path){
        alert(path);
    }
</script>
    </body>
</html>
