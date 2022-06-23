<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="utf-8">
<script>
	function update1() {//수정페이지로 이동
		let url = '/bbs/update/${dto.bbsno}'; //PathVarialbe사용하고있었으므로 절대경로로 바꿔줌
		location.href = url;
	}
	function del() {//삭제페이지로 이동
		let url = '/bbs/delete/${dto.bbsno}';
		url += '?oldfile=${dto.filename}';
		location.href = url;
	}
	function reply() {//답변페이지로 이동
		let url = '/bbs/reply/${dto.bbsno}';
		location.href = url;
	}
	function delete_Ajax() {
		var url = "/bbs/delete_Ajax";
		url += "?bbsno=${dto.bbsno}";
		url += "&oldfile=${dto.filename}";
		//alert(url);    
		location.href = url;
	}
	function list(){
		let url = '/bbs/list';
		url +="?nowPage=${param.nowPage}"
		url +="&col=${param.col}";
		url +="&word=${param.word}";
		location.href=url;
	}
</script>
</head>
<body>
	<div class="container">
		<h1>조회</h1>
		<div class="panel panel-default">
			<div class="panel-heading">작성자</div>
			<div class="panel-body">${dto.wname}</div>
			<div class="panel-heading">제목</div>
			<div class="panel-body">${dto.title}</div>
			<div class="panel-heading">내용</div>
			<div class="panel-body" >${dto.content}</div>
			<div class="panel-heading">조회수</div>
			<div class="panel-body">${dto.viewcnt}</div>
			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.wdate}</div>
			<div class="panel-heading">파일명</div>
			<div class="panel-body">${dto.filename}</div>
		</div>
		<div>
			<button onclick="location.href='/bbs/create'">등록</button>
			<button onclick="update1()">수정</button>
			<button onclick="del()">삭제</button>
			<button onclick="reply()">답변</button>
			<button onclick="list()">목록</button>
			<button onclick="delete_Ajax()">삭제2</button>
		</div>
		<br>

		<div class='row'>

			<div class="col-lg-12">

				<!-- panel start-->
				<div class="panel panel-default">

					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> 댓글
						<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
							Reply</button>
					</div>


					<div class="panel-body">

						<ul class="chat list-group"> 
							<li class="left clearfix" data-rno="12">
								<div>
									<div class="header">
										<strong class="primary-font">user1</strong> <small
											class="pull-right text-muted">2019-05-12</small>
									</div>
									<p>Good job!</p>

								</div>
							</li>
						</ul>
						<!-- ul end  -->
					</div>

					<div class="panel-footer"></div>

				</div>
				<!-- panel end-->
			</div>
			<!--  col-lg-12 end -->
		</div>
		<!-- row end -->

	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>내용</label>
						<textarea cols="10" rows="3" class="form-control" name='content'>New Reply!!!!</textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
					<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
					<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->




	<!-- 페이지 로딩시 댓글 목록 처리-->
	<!-- jstl는 internal javascript에서 사용가능 -->
	<script>
  let bbsno = "${dto.bbsno}"; 
  let sno = "${sno}";
  let eno = "${eno}";
 <!-- 댓글용 paging, 게시판 검색 -->
  let nPage = "${nPage}";
  let nowPage = "${param.nowPage}";
  let colx = "${param.col}";
  let wordx = "${param.word}";
 </script>
	<!-- 댓글처리 관련 Javascript 파일 추가-->
	<script src="${pageContext.request.contextPath}/js/producer.js"></script>

	<script src="${pageContext.request.contextPath}/js/consumer.js"></script>

</body>
</html>
