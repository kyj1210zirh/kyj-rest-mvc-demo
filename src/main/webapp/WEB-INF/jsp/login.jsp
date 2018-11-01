<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.4/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.4.2/js/all.js"
	integrity="sha384-wp96dIgDl5BLlOXb4VMinXPNiB32VYBSoXOoiARzSTXY+tsK8yDTYfvdTyqzdGGN"
	crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").submit(function (event){
			event.preventDefault();
			ajax_submit();
		});
	});
	
	function ajax_submit(){
		var data = {};
		data["userName"] = $("input[name='userName']").val();
		data["password"] = $("input[name='password']").val();
		
		$.ajax({ // form submission via ajax
			url : $("#loginForm").attr('action'), // form submission url
			type : 'POST', // request type
			dataType : 'application/json', // data type
			data : JSON.stringify(data), // get all data from the form
			success : function(result) {
				var json = "<h4>Ajax Response</h4><pre>"
	                + JSON.stringify(data, null, 4) + "</pre>";
	            $('#feedback').html(json);

	            console.log("SUCCESS : ", data);
			},
			error : function() { // response back from server in case of failure 
				var json = "<h4>Ajax Response</h4><pre>"
	                + JSON.stringify(data, null, 4) + "</pre>";
	            $('#feedback').html(json);

	            console.log("SUCCESS : ", data);
			}
		});
	}
</script>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/users/login"
		method="post" modelAttribute="loginModel" id="loginForm">
		<c:if test="${param.error != null}">
			<i class="failed">Sorry! You entered invalid username or password.</i>
		</c:if>
		<p>
			User name:
			<form:input type="text" name="userName" path="userName" />
		</p>
		<p>
			Password:
			<form:input type="password" name="password" path="password" />
		</p>
		<input type="submit" value="Login" />
	</form:form>
	<div id="feedback"></div>
</body>
</html>