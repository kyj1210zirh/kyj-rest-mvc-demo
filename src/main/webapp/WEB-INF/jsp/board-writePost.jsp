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
<script type="text/javascript" th:inline="javascript">
	$(document).ready(function() {
		if (window.location.href.indexOf("modifyPost") > -1) {
			$('#Heading').text("Modify Post");
			$('#userName').attr("disabled", true);
			$('#password').attr("disabled", true);
			$('#password').attr("readonly", true);
		} else {
			$('#Heading').text("New Post");
		}
	});
</script>
</head>
<body>
	<div class="" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="edit">
		<div class="modal-dialog modal-lg">
			<form:form class="modal-content" action="writePost" method="POST"
				modelAttribute="PostRequestModel" autocomplete="off">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading"></h4>
				</div>
				<div class="modal-body">
					<form:input id="postId" path="id" type="hidden" />
					<div class="form-group">
						<form:input id="userName" class="form-control " type="text" path="userName" placeholder="Your Name" required="required"/>
					</div>
					<div class="form-group">
						<form:input id="password" class="form-control" type="password" path="password" placeholder="Password" required="required"/>
					</div>
					<div class="form-group">
						<form:input id="title" class="form-control " type="text" path="title" placeholder="Title" required="required"/>
					</div>
					<div class="form-group">
						<form:textarea id="content" rows="12" class="form-control" path="content" placeholder="Contents" required="required"/>
					</div>
				</div>
				<div class="modal-footer" id="saveBtn">
					<input type="submit" value="Save" class="btn btn-success btn-lg" style="width: 100%;" />
				</div>
				<div class="modal-footer" id="backToListBtn">
					<button type="button" class="btn btn-primary btn-lg"
						style="width: 100%;"
						onclick="window.location.href='${pageContext.request.contextPath}/board/list'; return false;">Back to list
					</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>