<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<style>
		.ctx{
			width:90%;
			margin: 20px auto;
		}
		table{
			width:90%;
			text-align:center;
			margin: 20px auto;
			border-collapse: collapse;
		}
		td{
			border:1px solid #000;
		}
	</style>
</head>
<body>
<div class="ctx">当前可用代理IP数量：<span id="count"></span></div>
<table id="tb">
	<thead>
		<tr>
			<td>IP</td>
			<td>端口</td>
			<td>源网站</td>
			<td>入库时间</td>
		</tr>
	</thead>
	<tbody id="tbody">
	</tbody>
</table>



<script type="text/javascript">
		
		function getdata(){
			$.ajax({
	            type: "GET",
	            url: "proxy/stat.do",
	            data: {},
	            dataType: "json",
	            success: function(data){
	            	$("#count").text(data.count);
	                var html = ''; 
	                $.each(data.data, function(i, item){
	                     html += "<tr><td>"+item.host+"</td>"
	                           + "<td>"+item.port+"</td>"
	                           + "<td>"+item.comeFrom+"</td>"
	                           + "<td>"+item.createTime+"</td></tr>";
	                });
	                $('#tbody').html(html);
	            }
	        });
		}
		
		$(function(){
			getdata();
		})
		
		setInterval("getdata()", 1000 * 10);
</script>
</body>
</html>
