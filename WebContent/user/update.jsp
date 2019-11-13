<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/include/nav.jsp"%>



<!-- Contact Area -->
<section class="contact_area">
	<div class="container">
		<div class="row">

			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=update" method="POST" id="contactForm" onsubmit="return validateCheck()">
					<input type="hidden" name="userProfile" value="${sessionScope.user.userProfile}">
					<input type="hidden" name="id" value="${sessionScope.user.id}">
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" name="username" value="${sessionScope.user.username}" maxlength="20" readonly>
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" required="required" placeholder="비밀번호" maxlength="20">
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" required="required" placeholder="비밀번호 확인" maxlength="20">
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<input type="email" class="form-control" name="email" value="${sessionScope.user.email}" maxlength="40" readonly>
						</div>
					</div>


					<div class="col-md-12">
						<div class="form-group float-right">
							<a class="blog_btn" href="#" onClick="goPopup();">주소찾기</a>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" name="address" id="roadFullAddr" required="required" placeholder="주소영역" maxlength="60" readonly>
						</div>
					</div>


					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">UPDATE</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<!-- Contact Area -->
<br>
<br>



<script>
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}
	//주소입력버튼 클릭시 콜백됨
	function jusoCallBack(roadFullAddr) {
		var juso = document.querySelector('#roadFullAddr');
		juso.value = roadFullAddr;
	}

	
	
	
	
	function validateCheck() {

		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;

		if (password === passwordCheck) {
			console.log("비밀번호가 동일합니다");
			return true;
		} else {
			console.log("비밀번호 틀림");
			alert("비밀번호를 다시확인하세요");
			return false;
		}

	}
</script>


<%@ include file="/include/footer.jsp"%>