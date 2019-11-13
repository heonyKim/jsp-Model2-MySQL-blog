<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/blog/js/jquery-3.2.1.min.js"></script>
</head>
<body>

	<div id="img__wrap"></div>

	<!-- MIME TYPE -->
	<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">
		<input type="file" name="userProfile" id="img__input"><br>
		<img id="userProfilePreview" src="#">
		<input type="submit" value="전송">
	</form>

	
	<script>
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#userProfile').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#img__input").change(function() {
        readURL(this);
    });		
	</script>
	
</body>
</html>