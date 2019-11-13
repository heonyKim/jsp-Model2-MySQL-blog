<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- import nav.jsp -->
<%@ include file="/include/nav.jsp"%>



<div class="row">
<div class="col-lg-4"></div>
<div class="col-lg-4">
<div class="card" style="width:400px">
  <img class="card-img-top" id="userProfilePreview" src="${sessionScope.user.userProfile }" alt="Card image">
  <form action="/blog/user?cmd=profileimg" method="post" enctype="multipart/form-data">
 	 <input class="btn btn-primary" type="file" name="userProfile" id="img__input" >
  <div class="card-body">
    <p class="card-text">이미지를 클릭하시면<br>사진을 변경 할 수 있습니다.</p>
    <input type="submit" class="btn btn-primary" value="수정완료">
  </div>
  </form>
</div>
</div>
<div class="col-lg-4"></div>
</div>
<br>

<!--------------- script area --------------->
<script src="/blog/js/jquery-3.2.1.min.js"></script>
<script>
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#userProfilePreview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
            console.log(input.target.result);
        }
    }

    $("#img__input").change(function() {
        readURL(this);
    });		
	</script>

<!-- -------------------------------------- -->
<!-- import footer.jsp -->
<%@ include file="/include/footer.jsp"%>