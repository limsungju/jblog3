<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="<c:url value='/assets/js/jquery/jquery-1.9.0.js'/>"></script>
<script>
 $(function(){
	 // 카테고리 추가 & 리스트
	$('#submit').click(function(){
		var id = "${authUser.id}";
		var name = $('#name').val();
		var contents = $('#contents').val();
		
		if(name == '' || contents == '') {
			return;
		}
		
		var categoryVo = {id:id, name:name, contents:contents}
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/blog/categoryWrite", 
			type: "post",
			dataType: "json",
			data: categoryVo,
			success: function(response){
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				$("#category_list").empty(); // 태그는 남기고 내용 지우기
				
				let categoryList = response.data
				
				for(var i in categoryList) {
					$('#category_list').append($('<tr/>').append($('<td/>').text(categoryList[i].no))
							.append($('<td/>').text(categoryList[i].name))
							.append($('<td/>').text(categoryList[i].postCount))
							.append($('<td/>').text(categoryList[i].contents))
							.append($('<td/>').append($('<img/>')
									.attr('src', '${pageContext.request.contextPath}/assets/images/delete.jpg')
									.attr('id', 'category_del')
									.attr('name', categoryList[i].no))))
				}
				
				$('#name').val("");
				$('#contents').val("");
			}
		});
	});
	 
	 // 카테고리 삭제
	 $(document).on('click', '#category_del', (function(event){
		 var id = "${authUser.id}";
		 var no = $(this).attr('name');
		 
		 var categoryVo = {id:id, no:no}
		 console.log(categoryVo);
		 
		 $.ajax({
			 url: "${pageContext.servletContext.contextPath }/api/blog/categoryDelete",
			type: "post",
			dataType: "json",
			data: categoryVo,
			success: function(response){
				console.log(response.data);
				console.log(response.result);
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				$("#category_list").empty(); // 태그는 남기고 내용 지우기
				
				let categoryList = response.data
				
				for(var i in categoryList) {
					$('#category_list').append($('<tr/>').append($('<td/>').text(categoryList[i].no))
							.append($('<td/>').text(categoryList[i].name))
							.append($('<td/>').text(categoryList[i].postCount))
							.append($('<td/>').text(categoryList[i].contents))
							.append($('<td/>').append($('<img/>')
									.attr('src', '${pageContext.request.contextPath}/assets/images/delete.jpg')
									.attr('id', 'category_del')
									.attr('name', categoryList[i].no))))
				}
				
				$('#name').val("");
				$('#contents').val("");
			}
		 });
	 }));
	 
});
	
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blognav.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blogadminnav.jsp" />
				
		      	<table class="admin-cat">
		      	<thead>
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      	</thead>
		      	<tbody id="category_list">
				<c:forEach items="${categoryList }" var="vo" varStatus="status">
		      		<tr>
						<td>${vo.no }</td>
						<td>${vo.name }</td>
						<td>${vo.postCount }</td>
						<td>${vo.contents }</td>
						<td><img id="category_del" name="${vo.no}" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr>
				</c:forEach>
				</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="contents"type="text" name="contents"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" id="submit" value="카테고리 추가"></td>
		      		</tr>
		      	</table>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2019
			</p>
		</div>
	</div>
</body>
</html>