<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- import nav.jsp -->
<%@ include file="/include/nav.jsp"%>

<!--================Blog Area =================-->
<section class="blog_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="blog_left_sidebar">


					<!-- 페이징 하기 -->
					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination">
						
							<c:if test="${param.page gt 1}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-1}&search=${search}" class="page-link" aria-label="Previous"> <span aria-hidden="true"> <span class="lnr lnr-chevron-left"></span>
								</span>
							</a>
							</li>
							</c:if>
							
							<c:if test="${param.page gt 2}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-2}&search=${search}" class="page-link">${param.page-2}</a></li>
							</c:if>


							<c:if test="${param.page gt 1}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-1}&search=${search}" class="page-link">${param.page-1}</a></li>
							</c:if>


							<li class="page-item active"><a href="#" class="page-link">${param.page}</a></li>
							
							
							<c:if test="${param.page lt maxPage}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+1}&search=${search}" class="page-link">${param.page+1}</a></li>
							</c:if>
							
							<c:if test="${param.page lt maxPage-1}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+2}&search=${search}" class="page-link">${param.page+2}</a></li>
							</c:if>
							
							<c:if test="${param.page lt maxPage}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+1}&search=${search}" class="page-link" aria-label="Next"> <span aria-hidden="true"> <span class="lnr lnr-chevron-right"></span>
								</span>
							</a></li>
							</c:if>
							
							
						</ul>
					</nav>
					<!-- 페이징 끝 -->

					<c:forEach var="board" items="${boards}">
						<!--시작-->
						<article class="blog_style1">
							<div class="blog_img">
								<img class="img-fluid" style="width: 100%; max-height: 340px;" src="${board.previewImg}" alt="">
							</div>
							<div class="blog_text">
								<div class="blog_text_inner">
									<div class="cat">
										<a class="cat_btn" href="#">${board.user.username }</a> <a href="#"><i class="fa fa-calendar" aria-hidden="true"></i> ${board.createDate }</a> <a href="#"><i class="fa fa-comments-o"
											aria-hidden="true"></i> ${board.readCount }</a>
									</div>
									<a href="#"><h4>${board.title}</h4></a>
									<div class="board_list_content">${board.content }</div>
									<a class="blog_btn" href="/blog/board?cmd=detail&id=${board.id}">Read More</a>
								</div>
							</div>
						</article>
						<!--끄읕 -->
					</c:forEach>


					<!-- 페이징 하기 -->
					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination">
						
							<c:if test="${param.page gt 1}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-1}&search=${search}" class="page-link" aria-label="Previous"> <span aria-hidden="true"> <span class="lnr lnr-chevron-left"></span>
								</span>
							</a>
							</li>
							</c:if>
							
							<c:if test="${param.page gt 2}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-2}&search=${search}" class="page-link">${param.page-2}</a></li>
							</c:if>


							<c:if test="${param.page gt 1}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page-1}&search=${search}" class="page-link">${param.page-1}</a></li>
							</c:if>


							<li class="page-item active"><a href="/blog/board?cmd=list&page=${param.page}&search=${search}" class="page-link">${param.page}</a></li>
							
							
							<c:if test="${param.page lt maxPage}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+1}&search=${search}" class="page-link">${param.page+1}</a></li>
							</c:if>
							
							<c:if test="${param.page lt maxPage-1}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+2}&search=${search}" class="page-link">${param.page+2}</a></li>
							</c:if>
							
							<c:if test="${param.page lt maxPage}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page+1}&search=${search}" class="page-link" aria-label="Next"> <span aria-hidden="true"> <span class="lnr lnr-chevron-right"></span>
								</span>
							</a></li>
							</c:if>
							
							
						</ul>
					</nav>
					<!-- 페이징 끝 -->

				</div>
			</div>
			<div class="col-lg-4">
				<div class="blog_right_sidebar">
					<aside class="single_sidebar_widget search_widget">
						<div class="input-group">
							<form action="/blog/board?cmd=list&page=1" method="post">
								<input type="text" class="form-control" name="search" placeholder="Search Posts">
								<span class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<i class="lnr lnr-magnifier"></i>
								</button>
								</span>
							</form>
						</div>
						<!-- /input-group -->
						<div class="br"></div>
					</aside>

					<aside class="single_sidebar_widget popular_post_widget">
						<h3 class="widget_title">Popular Posts</h3>


						<!-- 인기게시물 TOP3 -->
						<c:forEach var="board" items="${hotBoards}">

							<div class="media post_item">
								<img src="${board.previewImg}" width="100px" height="60px" alt="post">
								<div class="media-body">
									<a href="/blog/board?cmd=detail&id=${board.id}"><h3>${board.title }</h3></a>
									<p>${board.createDate}</p>
								</div>
							</div>

						</c:forEach>
						<!-- 인기게시물 TOP3 -->


						<div class="br"></div>
					</aside>
				</div>
			</div>
		</div>
	</div>
</section>


<!--================Blog Area =================-->

<!-- import footer.jsp -->
<%@ include file="/include/footer.jsp"%>
