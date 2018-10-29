<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시글 목록</title>
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
<script type="text/javascript">
	$(document).ready(function() {
		$("[data-toggle=tooltip]").tooltip();
	});
</script>
</head>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>My Board</h4>
			<div class="table-responsive">
				<table id="mytable" class="table table-bordred table-striped">
					<thead>
						<tr>
							<th width="7%">No</th>
							<th width="42%">Title</th>
							<th width="14%">Name</th>
							<th width="20%">Date</th>
							<th width="7%">Views</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(posts.content) ne 0}">
								<c:forEach items="${posts.content}" var="post" varStatus="status">
									<c:url var="viewPostLink" value="/board/PostDetails">
										<c:param name="id" value="${post.id}"/>
									</c:url>
									<tr>
										<td>${post.id}</td>
										<td><a name="viewPost" href="${viewPostLink}">
											<c:choose>
												<c:when test="${fn:length(post.title) > 35}">
													${fn:substring(post.title,0,34)}...
												</c:when>
												<c:otherwise>
													${post.title}
												</c:otherwise>
											</c:choose>
										</a></td>
										<td>${post.userName}<c:if test="${empty post.userId}"> &#60;A&#62;</c:if></td>
										<td>${fn:substring(post.regDate,0,16)}</td>
										<td>${post.views}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5" style="text-align: center;">작성된 글이 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

				<div class="clearfix"></div>
				<span data-placement="top" data-toggle="tooltip" title="New Post">
					<button class="btn btn-primary btn-xs" type="button" onclick="window.location.href='PostDetails'; return false;">
						<i class="fas fa-pencil-alt"></i>
					</button>
				</span>
				<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</div>
</html>