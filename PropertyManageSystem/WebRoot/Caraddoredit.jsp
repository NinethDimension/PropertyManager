<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>car</title>
   <link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery-1.11.3.mim.js" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery.min.js" />
	<style type="text/css">
		.carId{
			display: none;		
		}
	</style>
  </head>
  
  <body>
  <div class="container-fluid">
    <form action="${cvm.viewClass.action}" method="post"  class="form-horizontal">
       <div class="row">
         ${cvm.viewClass.message}
       </div>
		${cvm.input}
		
		<div class="form-group">
            <div class="col-sm-3 col-sm-offset-2">
                <input class="btn btn-primary" name="btn_submit" type="submit" value="确定">
            </div>
        </div>

    </form>
    </div>
  </body>
</html>
