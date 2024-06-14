<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<h1>게시판 목록</h1>
<h2>${boardList.size() }</h2>
<%-- ${boardList } --%>

전달정보 : ${mas } <hr>

<div class="box">
<div class="box-header with-border">
<h3 class="box-title">게시판 리스트(ALL)</h3>
</div>
</div>
<div class="box-body"></div>
<table class="table table-bordered">
<tbody><tr>
<th style="width: 10px">bno</th>
<th>Title</th>
<th>Content</th>
<th>writer</th>
<th>regdate</th>
<th>viewCnt</th>
<!-- <th style="width: 40px">Label</th> -->
</tr>

<c:forEach items="${boardList }" var="vo">
<tr>
<td>${vo.bno }</td>
<td>${vo.title }</td>
<td>${vo.content }</td>
<td>${vo.writer }</td>
<td>
<fmt:formatDate type="both" value="${vo.regdate }"/>
</td>
<td>
<span class="badge bg-red">${vo.viewcnt }</span>
</td>
</tr>
</c:forEach>



</tbody>
</table>




<!-- 모달창 -->
<div class="modal fade" id="modal-default" style="display: none;">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span></button>
<h4 class="modal-title">Default Modal</h4>
</div>
<div class="modal-body">
<p>One fine body…</p>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary">Save changes</button>
</div>
</div>

</div>

</div>




<!-- Jquery사용 -->
<script>
$(document).ready(function(){
	
	//* jsp페이지 구성요소의 실행 순서
	//JAVA - JSTL/EL - HTML/CSS - JS -Jquery	
// 	alert("${msg}");
	
	var result = "${msg}";
	
	if(result == "createOk"){
// 		alert("글쓰기 완료! 리스트 페이지로 이동!")
		$('#myModal').modal("show");
	}
	
	Swal.fire("아이티윌 테스트@");
	
});

</script>



<%@ include file="../include/footer.jsp"%>