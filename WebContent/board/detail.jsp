<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/include/nav.jsp"%>

<!--================Blog Area =================-->
<section class="blog_area single-post-area">
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="main_blog_details">
					<a href="#"><h4>${board.title}</h4></a>
					<div class="user_details">
						<div class="float-left">
							<c:if test="${board.userId eq sessionScope.user.id}">
								<a href="/blog/board?cmd=updateForm&id=${board.id}">update</a>
								<a href="/blog/board?cmd=delete&id=${board.id}">delete</a>
							</c:if>
						</div>
						<div class="float-right">
							<div class="media">
								<div class="media-body">
									<h5>${board.user.username}</h5>
									<p>${board.createDate}</p>
								</div>
								<div class="d-flex">
									<img src="${board.user.userProfile }" width="60px" height="60px" alt="">
								</div>
							</div>
						</div>
					</div>
					<!-- 본문 시작 -->
					<p>${board.content}</p>
					<!-- 본문 끝 -->
					<hr />
				</div>

				<!-- 댓글 시작 -->
				<!-- before -->
				<div class="comments-area" id="comments-area">
					<!-- prepend -->
					<c:forEach var="comment" items="${comments}">


						<!-- 댓글 아이템 시작 -->
						<div class="comment-list" id="comment-id-${comment.id}">
						
							<!-- id 동적으로 만들기 -->

							<div class="single-comment justify-content-between d-flex">
								<div class="user justify-content-between d-flex">
									<div class="thumb">
										<img src="${comment.user.userProfile }" width="60px" height="60px" alt="">
									</div>
									<div class="desc">
										<h5>
											<a href="#">${comment.user.username}</a>
										</h5>
										<p class="date">${comment.createDate}</p>
										<p class="comment">${comment.content}</p>
									</div>

								</div>

							
								<div class="reply-btn">
								<c:if test="${comment.userId eq sessionScope.user.id}">
									<button onClick="commentDelete(${comment.id})" class="btn-reply text-uppercase" style="display: inline-block; float: left; margin-right: 10px;">
										<i class="fa fa-trash" style="font-size: 20px;"></i>
									</button>
								</c:if>
									<button onClick="replyListShow(${comment.id})" class="btn-reply text-uppercase" style="display: inline-block; float: left; margin-right: 10px;">
										<i class="fa fa-file" style="font-size: 18px;"></i>
									</button>
									<button onClick="replyForm(${comment.id})" class="btn-reply text-uppercase">
										<i class="fa fa-edit" style="font-size: 15px;"></i>
									</button>
								</div>
							
							</div>
							
						</div>
						<!-- 댓글 아이템 끝 -->

						<!--대댓글 Area-->

					</c:forEach>
					<!-- append -->


				</div>
				<!-- after -->
				<!-- 댓글 끝 -->

				<!-- 댓글 쓰기 시작 -->
				<div class="comment-form" style="margin-top: 0px;">
					<h4 style="margin-bottom: 20px">Leave a Comment</h4>
					<form id="comment-submit">
						<input type="hidden" name="userId" value="${sessionScope.user.id}" /> <input type="hidden" name="boardId" value="${board.id}" />
						<div class="form-group">
							<textarea id="content" style="height: 60px" class="form-control mb-10" rows="2" name="content" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'"
								required=""></textarea>
						</div>
						<button type="button" onClick='commentWrite()' class="primary-btn submit_btn">Post Comment</button>
					</form>
				</div>
				<!-- 댓글 쓰기 끝 -->
			</div>

			<div class="col-lg-2"></div>
		</div>
	</div>
</section>

<!--================Blog Area =================-->

<%@ include file="/include/footer.jsp"%>

<!--================Comment Script =================-->
<script>
   
   function commentItemForm(id, username, content, createDate, userProfile){
       var commentItem = "<div class='comment-list' id='comment-id-"+id+"'> ";
       commentItem += "<div class='single-comment justify-content-between d-flex'> ";
       commentItem += "<div class='user justify-content-between d-flex'> ";
       commentItem += "<div class='thumb'> <img src='"+userProfile+"' width='60px' height='60px' alt=''> </div> ";
       commentItem += "<div class='desc'><h5><a href='#'>"+username+"</a></h5> ";
       commentItem += "<p class='date'>"+createDate+"</p><p class='comment'>"+content+"</p></div></div> ";
       commentItem += "<div class='reply-btn'>";
       commentItem += "<button onClick='commentDelete("+id+")' class='btn-reply text-uppercase' style='display:inline-block; float:left; margin-right:10px;'>삭제</button>";
       commentItem += "<button onClick='replyListShow("+id+")' class='btn-reply text-uppercase'  style='display:inline-block; float:left; margin-right:10px;'>보기</button>";
       commentItem += "<button onClick='replyForm("+id+")' class='btn-reply text-uppercase'>쓰기</button></div></div></div>";
       console.log(commentItem);
       return commentItem;
   }

   
   //아이템 동적으로 보기 위해서 필요함
   function replyItemForm(id, username, content, createDate, userProfile){
	      var replyItem = "<div class='reply-area'><div class='comment-list left-padding' style='display:block' id='reply-id-"+id+"'>";
	      replyItem+= "<div class='single-comment justify-content-between d-flex'>";
	      replyItem+= "<div class='user justify-content-between d-flex'>";
	      replyItem+= "<div class='thumb'><img src='"+userProfile+"' width='60px' height='60px' alt=''></div>";
	      replyItem+= "<div class='desc'><h5><a href='#'>"+username+"</a></h5>";
	      replyItem+= "<p class='date'>"+createDate+"</p>";
	      replyItem+= "<p class='comment'>"+content+"</p>";
	      replyItem+= "</div></div><div class='reply-btn'>";
	      
	      if( ${sessionScope.user.id} == reply.userId){
	    	  replyItem+= "<button onClick='replyDelete("+id+")' class='btn-reply text-uppercase'>삭제</button>";
	      }
	      
	      replyItem+= "</div></div></div></div>";
	      console.log(typeof id);
	      return replyItem;
	   }
   
   
   //flag
	    var flag = 1;
   //reply 보기 - ajax
   function replyListShow(comment_id) {
      //comment_id 로 reply 전부다 select 해서 가져오기
  
      $.ajax({
    	  method:"POST",
    	  url:"/blog/api/reply?cmd=list",
    	  data:comment_id + "",
    	  contentType: "text/plain; charset=utf-8",
          dataType: "json",
          success:function(replys){
        	  

        	  if(flag ===1){
        		  for(reply of replys){
						console.log(reply);
            		  var reply_et=replyItemForm(reply.id, reply.user.username, reply.content, reply.createDate, reply.user.userProfile);
            		  //화면표시
            		  $("#comment-id-"+reply.commentId).after(reply_et);
            	  } 
        		  flag=0;
        		  console.log("flag : "+flag);
        		  
        	  }else{

				  $(".reply-area").remove();	
				  flag=1;
				  console.log("flag : "+flag);
        	  }
        	  
        	  
        	  
        	  
        	  
          },
    	  error:function(xhr){
    		  console.log(xhr);
    	  }
      })

   }
   
   //reply 삭제
   function replyDelete(reply_id){
	   $.ajax({
		   method:"POST",
		   url:"/blog/api/reply?cmd=delete",
		   data:reply_id+"",
		   contentType: "text/plain; charset=utf-8", //MIME 타입
	       success: function(result){
	    	   if(result==="ok"){
	    		   //element삭제
	               $("#reply-id-"+reply_id).remove();
	    	   }
	       },
		   error:function(xhr){
			   console.log(xhr);
		   }
	   })
   }
   
 //reply 쓰기
   function replyWrite(){
      var reply_submit_string = $("#reply-submit").serialize();
      $.ajax({
         method: "POST",
         url: "/blog/api/reply?cmd=write",
         data: reply_submit_string,
         contentType: "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json",
         
         success: function(reply){
        	 console.log("reply-success까지 진입");
       		  var reply_et=replyItemForm(reply.id, reply.user.username, reply.content, reply.createDate, reply.user.userProfile);
       		  $("#comment-id-"+reply.commentId).after(reply_et);
       		  //화면표시
       		  
            $("#reply-content").val("");
            
         },
         error: function(xhr){
            console.log(xhr.status);
            console.log(xhr);
         }
         
      });
      
   }
   
   
   //comment 쓰기
   function commentWrite(){
      var comment_submit_string = $("#comment-submit").serialize();
      $.ajax({
         method: "POST",
         url: "/blog/api/comment?cmd=write",
         data: comment_submit_string,
         contentType: "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json",
         
         success: function(comment){
            //화면에 적용
            var comment_et = commentItemForm(comment.id, comment.user.username, comment.content, comment.createDate, comment.user.userProfile);
            $("#comments-area").append(comment_et);
            //입력폼 초기화하기
            $("#content").val("");
            
         },
         error: function(xhr){
            console.log(xhr.status);
            console.log(xhr);
         }
         
      });
      
   }
   
   
   //comment 삭제
   function commentDelete(comment_id){ //자바스크립트는 하이픈 사용 불가
      $.ajax({
         method: "POST",
         url: "/blog/api/comment?cmd=delete",
         data: comment_id+"",
         contentType: "text/plain; charset=utf-8", //MIME 타입
         success: function(result){
            console.log(result);
            //해당 엘레멘트(DOM)을 찾아서 remove() 해주면 됨.
            if(result === "ok"){
               $("#comment-id-"+comment_id).remove();
            }
            
         },
         error: function(xhr){
            console.log(xhr.status);
         }
      });
   }

   //reply Form 만들기  - 화면에 로딩!!
	
   var reply_flag = 1;
   function replyForm(comment_id) {
	   
	  if(reply_flag==1){
		  var comment_form_inner = "<h4 style='margin-bottom:20px'>Leave a Reply</h4><form id='reply-submit'><input type='hidden' name='commentId' value='"+comment_id+"' /><input type='hidden' name='userId' value='${sessionScope.user.id}' /><div class='form-group'><textarea style='height:60px' class='form-control mb-10' rows='2' id='reply-content' name='reply-content' placeholder='Messege' required=''></textarea></div><button type='button' onClick='replyWrite()'class='primary-btn submit_btn'>Reply</button></form>";

	      //<div class="comment-form" style="margin-top:0px;"></div>
	      var comment_form = document.createElement("div"); //div 빈 박스 생성
	      comment_form.className = "reply-form-area"; //div에 클래스 이름을 주고
	      comment_form.style = "margin-top:0px"; //div에 style을 준다.

	      comment_form.innerHTML = comment_form_inner;
	      console.log(comment_form);

	      var comment_list = document.querySelector("#comment-id-"+comment_id);
	      comment_list.append(comment_form); //after와 append, before와 prepend 
	      
	      reply_flag = 0;
	      
	  } else{
		  $(".reply-form-area").remove();	
		  reply_flag=1;
	  }
	   
      
   }
</script>
</body>
</html>



