<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Carlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery-1.11.3.mim.js" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/js/jquery.min.js" />
	
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" href="css/responsive-tables.min.css" type="text/css">
	
	<script type="text/javascript" src="js/jquery-1.11.0.min.js" ></script>
	

	
	
	<style type="text/css">
	    /* example demo page styles */
	   
	    table {
	        width: 100%;
	        margin: 1em 0 2em;
	        font-size: .938em;
	        border-collapse: collapse;
	    }
	    select,input {
	    	color: #000000;
	    	color:#000000;
	    }
		body{ 
			background-color:#ffffff;
		}
	    caption {
	        margin: 1em 0 .7em 0;
	        text-align: center;
	        font-weight: bold;
	        font-size: 120%;
	        letter-spacing: .5px;
	        color: #fff;
	    }

	    th {
	        text-align:center;
	        padding: .5em .5em;
	        font-weight: bold;
	        border-top: solid 3px #ccc;
	        border-bottom: solid 1px #ccc;
	        background: #66677c;
	    }

	    td {
	        padding: .5em .5em;
	        color:#663333;
	        text-align:center;
	        border-bottom: solid 1px #ccc;
	    }
		.active{
			background:#66677c;
		}
	</style>
	
	<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	
  </head>
  
  
  <body>
  <script type="text/javascript">
    function loadp(p){
    		$('tbody tr').each(function(){
    		var cc = $(this).attr('class');
    		if(cc==p)
    		{
    			$(this).show();
    		}
    		else{
    			$(this).hide();
    		}
    	});
    }
   	function submitlike(){
   		var propertyName = $('#propertyName').val();
   		var value=$('#value').val();
   		
   		//alert("propertyName"+propertyName+"value"+value)
   		/*$.ajax({
		   		url: ${cvm.viewClass.action},
		   		type: 'post',
		   		data: {proertyName: propertyName,
		   				value : value
		   			  },
		   		success:function(){*/
   		$('#tables').load("carAssign!likelist",{"propertyName":propertyName,"value":value});
   				//}
   			  //})
   			  
   	}
    
    $(document).ready(function (){
    	loadp(1)
    	 $.responsiveTables(); 
    	$('.delete').click(function(){
    	if(confirm("您确定要删除吗")){
    		window.location.href=$(this).attr('hs')
    		
    	}
    	})
    	
    	
    	$('.page').click(function(){
    		var va= $(this).attr('ps')
    		$('.active').removeClass('active')
    		$(this).attr('class','active')
    		loadp(va)
    	})
    	
    	
    	
    	$('.pagelast').click(function(){
    		
    		var va=$('.active').attr('ps')
    		var vi=parseInt(va)
    		//alert(vi)
    		if(vi>1){
	    		$('.active').removeClass('active')
	    		va= vi-1
	    		
	    		$('#'+va).attr('class','active')
	    		loadp(va)
    		}
    		
    	})
    	
    	
    	$('.pagenext').click(function(){
    		var total=$('nav').attr('total')
    		var totali=parseInt(total)
    		//alert(totali)
    		var va=$('.active').attr('ps')
    		var vi=parseInt(va)
    		if(vi<totali){
    		$('.active').removeClass('active')
    		//alert(va)
    		va= vi+1
    		//alert(va)
    		$('#'+va).attr('class','active')
    		loadp(va)
    		}
    	})
    	
    })//readyend;
    
    	
    </script>
  	${cvm.viewClass.add}
    ${cvm.list}
    
    
    <script type="text/javascript"  src="js/jquery.responsive-tables.min.js"></script>
    
  </body>
</html>
