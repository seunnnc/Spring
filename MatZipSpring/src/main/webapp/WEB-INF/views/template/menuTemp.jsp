<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/res/css/common.css?ddd=111">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item}.css">
</c:forEach>
<title>${title}</title>
</head>
<body>
	<div id="container">
		<header>
			<div id="headerLeft">
				<c:if test="${loginUser != null}">
					<div class="containerPImg mL10" >
						<c:choose>
							<c:when test="${loginUser.profile_img != null}">
								<img class="pImg" src="/res/img/user/${loginUSer.i_user}/${loginUser.profile_img}">
							</c:when>
							<c:otherwise>
								<img class="pImg" src="/res/img/default_profile_img.png">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="mL10"><span id="user_nm">${loginUser.nm}</span>님 환영합니다 </div>
					<div class="mL15" id="headerLogout"><a href="/user/logout">로그아웃</a></div>
				</c:if>
				<c:if test="${loginUser == null}">
					<div class="ml15" id="headerLogout"><a href="/user/login">로그인</a></div>
				</c:if>
			</div>
			<div id="headerRight">
				<a href="/rest/map" id="map">
					<i class="fas fa-map-marked-alt"></i>
				</a>
				<c:if test="${loginUser != null}">
				<a class="mL20" href="#" onclick="moveToReg()">
					<i class="fas fa-plus"></i>
				</a>
				</c:if>
				<c:if test="${loginUser == null}">
					<a class="mL20" href="#" onclick="alert('로그인이 필요합니다')">
						<i class="fas fa-plus"></i>
					</a>
				</c:if>
				<a class="mL20" href="/user/restFavorite">
					<i class="fas fa-bookmark"></i>
				</a>
			</div>
		</header>
		<section>
			<jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
		</section>
		<footer>
			회사정보
		</footer>
	</div>
	
	<script>
		function moveToReg() {
			location.href='/rest/reg'
		}	
	</script>
</body>
</html>