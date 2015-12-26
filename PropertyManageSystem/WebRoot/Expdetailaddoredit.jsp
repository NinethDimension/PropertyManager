<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Expdetailaddoredit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery-1.11.3.mim.js" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery.min.js" />
	
	<script type="text/javascript" src="js/jquery-1.11.0.min.js" ></script>
	<style type="text/css">
		.expdetailId{
			display: none;		
		}
	</style>
  </head>
  
  <body>
       <div class="container-fluid">
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
    
    <script type="text/javascript">
    	$(document).ready(function(){
    	
    	$.post(  
              
                "expensetypeAssign!fkfind",        //服务器要接受的url  
                   //传递的参数       
                  
               function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
                  
                   alert(data.str);
                   var ad=  data.str;
                   ad=ad.replace(/&lt;/g,"<")
                   ad=ad.replace(/&gt;/g,">")
                    $('#expenseId').append(ad)
                }, 'json'); 
    	
    	//load("expensetypeAssign!fkfind")
    	
    	})
    	
    </script>
    </div>
  </body>
</html>
