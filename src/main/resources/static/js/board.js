//통계함수 호출
getPageNum()
getBoardStatisticsa();

function getBoardStatisticsa() {
	$.ajax({
		url: "/api/v1/board/statisticsa",
		type: "Get",
		dataType: "json",
		success: function(response) {
			//text or html : input을 제외한 태그를 컨트롤 할때 사용
			//val : input 컨트롤할떄 사용
			$("#studentsCnt").text(response.studentsCnt);
			$("#boardCnt").text(response.boardCnt);
			$("#WriterCnt").text(response.WriterCnt);
			$("#viewsCnt").text(response.viewsCnt);
		},
	});
}


function getPageNum() {
	var pageNum = $('#nowPageNum').val();
	$("#pageNum" + pageNum).css("background-color", "#287bff");
	$("#pageNum" + pageNum).css("color", "#fff");
}

function getBoardList(pageNum, pageSize) {
	location.href = "/board?pageNum=" + pageNum + "&pageSize=" + pageSize;
}

//게시글드러나는함수
function getBoard(boardId) {
	$(".update-popup").css("display", "block");
	//ajax 작성
	$.ajax({
		url: "/api/v1/board/boardId/" + boardId,
		type: "GET",
		dataType: "json",
		success: function(response) {
			//3. input에 date set해주기
			$("#upt-title").val(response.title);
			$("#upt-content").val(response.content);
			$("#boardIdHidden").val(boardId);
			setBoardViews(boardId); //조회 수 함수
		},
	});
}

//조회수 증가 함수
function setBoardViews(boardId) {
	$.ajax({
		url: "/api/v1/board/views/boardId/" + boardId,
		type: "PATCH",
		dataType: "json", //서버결과 json으로요청
		success: function(response) {
			if (response > 0) {
				//에러 페이지로 이동하는 로직
			}
		},
	});
}

//게시글등록
$("#contentSubmit").click(function() {
	if (confirm("게시글을 작성하시겠습니까?")) {
		var studentsId = $("#studentsId").val();
		var title = $("#title").val();
		var content = $("#content").val();

		if (title == "") {
			alert("제목을 입력 해주세요");
			$("#title").focus();
			return false;
		}
		if (content == "") {
			alert("제목을 입력 해주세요");
			$("#content").focus();
			return false;
		}

		var jsonData = {
			studentsId: studentsId,
			title: title,
			content: content,
		};

		$.ajax({
			url: "/api/v1/board",
			type: "POST",
			contentType: "application/json", //서버에 json타입으로 요청
			dataType: "json", //서버결과 json으로요청
			data: JSON.stringify(jsonData),
			success: function(reponse) {
				if (reponse > 0) {
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10);
				}
			}, //success끝
		}); //ajax끝
	} //if끝
}); //함수끝

//게시물 수정 하는 함수
$("#contentUpdate").click(function() {
	//1. 게시판 번호 확인
	var boardId = $("#boardIdHidden").val(); //hidden에 숨겨둔 boardId가져오기.
	//2. JSON생성
	var title = $("#upt-title").val();
	var content = $("#upt-content").val();

	var jsonData = {
		title: title,
		content: content,
	};
	//3. AJAX
	$.ajax({
		url: "/api/v1/board/boardId/" + boardId,
		type: "PATCH",
		contentType: "application/json", //서버에 json타입으로 요청
		dataType: "json", //서버결과 json으로요청
		data: JSON.stringify(jsonData),
		success: function(response) {
			if (response > 0) {
				alert("수정 완료");
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10);
			}
		},
	});
});

//게시물 삭제
$("#contentDelete").click(function() {
	//1. 게시판 번호 확인
	var boardId = $("#boardIdHidden").val(); //hidden에 숨겨둔 boardId가져오기.
	//2. JSON생성
	var title = $("#upt-title").val();
	var content = $("#upt-content").val();

	var jsonData = {
		title: title,
		content: content,
	};
	//3. AJAX
	if (confirm("정말로 삭제하시겠습니까?")) {
		$.ajax({
			url: "/api/v1/board/boardId/" + boardId,
			type: "DELETE",
			contentType: "application/json", //서버에 json타입으로 요청
			dataType: "json", //서버결과 json으로요청
			data: JSON.stringify(jsonData),
			success: function(response) {
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10);
			},//success끝
		});//ajax끝
	}//config
});//삭제함수 끝


$('#searchBar').keyup(function(key) {
	var pageSize = 10;
	var pageNum = 1;
	if (key.keyCode == 13) {
		var search = $('#searchBar').val().trim();//input에 작성한 작성자를 가져옴
		if (search != '') {
			location.href = "/board/search?writer=" + search + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
		}
	}
});