<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
	integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
	crossorigin="anonymous">
<div style="width: 100%; margin-top: 15px;">
	<div class="recMenuContainer">
		<c:forEach items="${recMenuList}" var="item">
			<div class="recMenuItem" id="recMenuItem_${item.seq }">
				<div class="pic">
					<c:if test="${item.menu_pic != null and item.menu_pic != ''}">
						<img src="/res/img/rest/${data.i_rest}/rec_menu/${item.menu_pic}">
					</c:if>
				</div>
				<div class="info">
					<div class="nm">${item.menu_nm}</div>
					<div class="price">
						<fmt:formatNumber type="number" value="${item.menu_price}" />
					</div>
					<c:if test="${loginUser.i_user == data.i_user}">
						<div class="delIconContainer" onclick="delRecMenu(${item.seq})">
							<span><i class="fas fa-times"></i></span>
						</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="sectionContainerCenter">
		<div>
			<c:if test="${loginUser.i_user == data.i_user}">
				<button onclick="isDel()">맛집 삭제</button>
				<h2><i class="fas fa-utensils"></i> 추천 메뉴</h2>
				<div>
					<form id="recFrm" action="/rest/recMenus" enctype="multipart/form-data" method="post">
						<div>
							<button type="button" onclick="addRecMenu()">추천 메뉴 추가</button>
						</div>
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<div id="recItem"></div>
						<div>
							<button type="submit" style="width : 50px;">등록</button>
						</div>
					</form>
				</div>

				<h2><i class="fas fa-utensils"></i> 메뉴</h2>
				<div>
					<form id="menuFrm" action="/rest/menus"
						enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<input type="file" name="menu_pic" multiple>
						<div>
							<button type="submit">등록</button>
						</div>
					</form>
				</div>
			</c:if>

			<div class="restaurant-detail">
				<div id="detail-header">
					<div class="restaurant_title_wrap">
						<span class="title">
							<h1 class="restaurant_name">${data.nm}</h1>
						</span>
					</div>
					<div class="status_branch_name">
						<span class="cnt_hit">${data.hits}</span> <span
							class="cnt_favorite">${data.cnt_favorite}</span>
					</div>
				</div>
				<div>
					<table>
						<caption class="sr-only">레스토랑 상세정보</caption>
						<tbody>
							<tr>
								<td>주소</td>
								<td>${data.addr}</td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td>${data.cd_category_nm}</td>
							</tr>
							<tr>
								<td>작성자</td>
								<td>${data.user_nm}</td>
							</tr>
							<tr>
								<th>메뉴</th>
								<td>	
									<div class="menuList">
										<c:if test="${fn:length(menuList) > 0}">
											<c:forEach var="i" begin="0" end="${fn:length(menuList) > 3 ? 2 : fn:length(menuList) - 1}">
												<div class="menuItem">
													<img src="/res/img/rest/${data.i_rest}/menu/${menuList[i].menu_pic}">
													<c:if test="${loginUser.i_user == data.i_user}">
														<div class="delIconContainer" onclick="delMenu(${menuList[i].seq})">
															<span><i class="fas fa-times"></i></span>
														</div>
													</c:if>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${fn:length(menuList) > 3}">
											<div class="menuItem bg_black">
												<div class="moreCnt">
													+${fn:length(menuList) - 3}
												</div>
											</div>
										</c:if>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		function delRecMenu(seq) {
			
			axios.get('/rest/ajaxDelRecMenu', {
				params: {
					i_rest: ${data.i_rest},
					seq
				}
			}).then(function(res) {
				if(res.data == 1) {
					var ele = document.querySelector('#recMenuItem_' + seq)
					ele.remove()
				}
				location.reload()
			})
		}
	
		function isDel(){
			if(confirm('삭제 하시겠습니까?')) {
				location.href='/rest/del?i_rest=${data.i_rest}'
			}
		}
		
		var idx = 0;

		function addRecMenu() {
			var div = document.createElement('div')
			div.setAttribute('id', 'recMenu_' + idx++)
			
			var inputNm = document.createElement('input')
			inputNm.setAttribute("type", "text");
			inputNm.setAttribute('name', 'menu_nm')
			var inputPrice = document.createElement('input')
			inputPrice.setAttribute("type", "number")
			inputPrice.setAttribute('name', 'menu_price')
			inputPrice.vlaue = '0'
			var inputPic = document.createElement('input')
			inputPic.setAttribute("type", "file");
			inputPic.setAttribute('name', 'menu_pic')
			
			var delBtn = document.createElement('input')
			delBtn.setAttribute('type', 'button')
			delBtn.setAttribute('value', 'X')
			delBtn.addEventListener('click', function(idx) {
				div.remove()
			})
			
			
			div.append('메뉴 : ')
			div.append(inputNm)
			div.append(' 가격 : ')
			div.append(inputPrice)
			div.append(' 사진 : ')
			div.append(inputPic)
			div.append(delBtn)
			
			recItem.append(div)
		}
		
		
	</script>
</div>