<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"
	integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns"
	crossorigin="anonymous">
<script defer
	src="https://use.fontawesome.com/releases/v5.4.2/js/all.js"
	integrity="sha384-wp96dIgDl5BLlOXb4VMinXPNiB32VYBSoXOoiARzSTXY+tsK8yDTYfvdTyqzdGGN"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript" th:inline="javascript">
	$(document).ready(function() {
		if(paramCheck('id')==null){
			$('#Heading').text("New Post");
			$('#editBtn').addClass("d-none");
			$('#saveBtn').removeClass("d-none");
		}else{
			$('#Heading').text("View Post");
			$('#userName').attr("disabled",true);
			$('#title').attr("disabled",true);
			$('#content').attr("disabled",true);
		}
	});
	
	function editPost(){
		$('#userName').attr("disabled",false);
		$('#passwordIn').removeClass("d-none");
		$('#title').attr("disabled",false);
		$('#content').attr("disabled",false);
		$('#editBtn > button').addClass("d-none");
		$('#editBtn > input').removeClass("d-none");
	}
	
	function paramCheck(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return null;
	    }
	    else{
	       return results[1] || 0;
	    }
	}
</script>
</head>
<body>

<div class="" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit">
	<div class="modal-dialog modal-lg">
		<form:form class="modal-content" action="writePost" method="POST" modelAttribute="PostRequestModel" autocomplete="off">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<h4 class="modal-title custom_align" id="Heading"></h4>
			</div>
			<div class="modal-body">
				<form:input path="id" class="d-none"/>
				<div class="form-group">
					<form:input id="userName" class="form-control " type="text" path="userName" placeholder="Your Name" />
				</div>
				<div class="form-group d-none" id="passwordIn">
					<form:input id="password" class="form-control" type="password" path="password" placeholder="Password" />
				</div>
				<div class="form-group">
					<form:input id="title" class="form-control " type="text" path="title" placeholder="Title" />
				</div>
				<div class="form-group">
					<form:textarea id="content" rows="8" class="form-control" path="content" placeholder="Contents" />
				</div>
			</div>
			<div class="modal-footer" id="editBtn">
				<button type="button" class="btn btn-warning btn-lg" style="width: 100%;" onclick="editPost();">Edit</button>
				<input type="submit" value="Edit" class="btn btn-warning btn-lg d-none" style="width: 100%;"/>
			</div>
			<div class="modal-footer d-none" id="saveBtn">
				<input type="submit" value="Save" class="btn btn-success btn-lg" style="width: 100%;" />
			</div>
			<div class="modal-footer" id="backToListBtn">
				<button type="button" class="btn btn-primary btn-lg" style="width: 100%;" onclick="window.location.href='list'; return false;">Back to list</button>
			</div>
		</form:form>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


</body>
</html>