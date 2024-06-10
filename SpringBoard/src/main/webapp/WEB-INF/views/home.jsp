<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding ="UTF-8" %>

<%@ include file="include/header.jsp" %>


<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input type="button" value="버튼" class="btn-lg btn-success">

<div class="col-md-6">
<button type="button" class="btn btn-primary btn-block"><i class="fa fa-bell"></i> 로그인</button>
<button type="button" class="btn btn-info btn-block btn-flat"><i class="fa fa-bell"></i>구글 계정 로그인</button>
<button type="button" class="btn btn-danger btn-block btn-sm"><i class="fa fa-bell"></i>카카오톡 계정 로그인</button>
</div>

<%@ include file="include/footer.jsp" %>