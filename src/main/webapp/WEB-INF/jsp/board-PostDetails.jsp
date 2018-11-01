<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel='stylesheet'
	href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.4/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/additional-methods.min.js"></script>
<script type="text/javascript" src="/js/messages_ko.js"></script>
<script type="text/javascript" th:inline="javascript">
	$(document).ready(function() {
		$("#PostDetails").submit(function (event){
			event.preventDefault();
			ajax_submit();
		});
	});

	function ajax_submit() {
		var returnValue=false;
		var data = {}
		data["id"] = $("#postId").val();
		data["pass"] = $("#password").val();
		
		$("#passConfirm").prop("disabled", true);

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/board/postPassChk",
			data : JSON.stringify(data),
			contentType: 'application/json',
			cache : false,
			timeout : 600000,
			success : function(response) {
				if(response.result==true){
					returnValue=response.result;
 				}else{
					alert(response.msg);
				}
				$("#passConfirm").prop("disabled", false);
			},
			error : function(e) {
				console.log("ERROR : ", e);
				$("#passConfirm").prop("disabled", false);
			},complete: function(){
				if(returnValue){
					$("#PostDetails").off('submit');
					$("#PostDetails").attr('action', data["id"]);
					$("#PostDetails").submit();
				}
			}
		});
	}

	function modifyPost() {
		$('#password').attr("type","password");
		$('#title').attr("readonly",false);
		$('#content').attr("readonly",false);
		$("input[name='_method']").val("PUT");
	}
	
	function deletePost() {
		$('#password').attr("type","password");
		$("input[name='_method']").val("DELETE");
	}
</script>
</head>
<body>

	<div class="" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="edit">
		<div class="modal-dialog modal-lg">
			<form:form class="modal-content" modelAttribute="PostRequestModel" autocomplete="off" id="PostDetails" method="POST">
				<input type="hidden" name="_method"/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">View Post</h4>
				</div>
				<div class="modal-body">
					<form:input id="postId" path="id" type="hidden" disabled="true"/>
					<div class="form-group">
						<form:input id="userName" class="form-control " type="text" path="userName" placeholder="Your Name" readonly="true"/>
					</div>
					<div class="form-group">
						<form:input id="password" class="form-control" type="hidden" path="password" placeholder="Password" required="required"/>
					</div>
					<div class="form-group">
						<form:input id="title" class="form-control " type="text" path="title" placeholder="Title" readonly="true"/>
					</div>
					<div class="form-group">
						<form:textarea id="content" rows="12" class="form-control" path="content" placeholder="Contents" readonly="true"/>
					</div>
				</div>
				<div class="modal-footer" id="editBtn">
					<button type="submit" class="btn btn-warning btn-lg"
						style="width: 100%;" onclick="modifyPost()" id="SubmitModify" value="PUT">Modify</button>
					<button type="submit" class="btn btn-danger btn-lg"
						style="width: 100%;" onclick="deletePost()" id="SubmitDelete" value="DELETE">Delete</button>
				</div>
				<div class="modal-footer" id="backToListBtn">
					<button type="button" class="btn btn-primary btn-lg"
						style="width: 100%;"
						onclick="window.location.href='${pageContext.request.contextPath}/board/list'; return false;">Back to list</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>