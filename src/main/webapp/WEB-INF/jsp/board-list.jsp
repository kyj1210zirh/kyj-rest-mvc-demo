<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시글 목록</title>
<link rel='stylesheet' href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
<script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.4/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.4.2/js/all.js"
	integrity="sha384-wp96dIgDl5BLlOXb4VMinXPNiB32VYBSoXOoiARzSTXY+tsK8yDTYfvdTyqzdGGN"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("[data-toggle=tooltip]").tooltip();
		let searchParams = new URLSearchParams(window.location.search);
		if(searchParams.has('limit')){
			let param = searchParams.get('limit')
			$('#oplimit option:selected').removeAttr("selected");
			$('#oplimit').find('#' + param).prop("selected",true);
		}
	});
	
	function changeLimit(){
		var limit = {limit: $('#oplimit option:selected').val()};
		location.search = $.param(limit);
	}
</script>
</head>
<div class="container">
	<header>
      <nav class="navbar navbar-expand-md navbar-dark bg-dark">
          <form:form action="search" method="POST" class="form-inline mt-2 mt-md-0" >
          	<select class="form-control mr-sm-2" name="searchOption">
          		<option value="1">제목</option>
          		<option value="2">내용</option>
          		<option value="3">제목+내용</option>
          		<option value="4">작성자</option>
          	</select>
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchWord">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form:form>
      </nav>
    </header>
	<div class="row" style="margin-top: 5px;">
		<div class="col-md-12">
			<div>
				<h4><a class="text-dark" style="text-decoration: none" href="/board/list">My Board</a></h4>
				<select onchange="changeLimit()" id="oplimit" style="float: right">
					<option value="20" id="20">20개</option>
					<option value="30" id="30">30개</option>
					<option value="50" id="50">50개</option>
				</select>
			</div>
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
									<c:url var="viewPostLink" value="/board/PostDetails/${post.id}">
										<%-- <c:param name="id" value="${post.id}"/> --%>
									</c:url>
									<tr>
										<td>${post.id}</td>
										<td><a class="text-dark" name="viewPost" href="${viewPostLink}">
											<c:choose>
												<c:when test="${fn:length(post.title) > 30}">
													${fn:substring(post.title,0,29)}...
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
					<button class="btn btn-primary btn-xs" type="button" onclick="window.location.href='writePost'; return false;">
						<i class="fas fa-pencil-alt"></i>
					</button>
				</span>
				<!-- <nav aria-label="Page navigation example">
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
				</nav> -->
			</div>
		</div>
	</div>
</div>
</html>