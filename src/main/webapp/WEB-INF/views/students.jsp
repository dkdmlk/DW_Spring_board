<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/static/css/board.css" />
  </head>
  <body>
    <div class="container">
      <div class="student-popup">
        <div class="editor">
          <div class="input-box">
            <label for="studentsName">학생이름 : </label>
            <input
              id="studentsName"
              type="text"
              value=""
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="title">비밀번호 : </label>
            <input
              id="studentsPassword"
              type="text"
              placeholder="비밀번호를 입력하세요..."
            />
          </div>
          <div class="btn-area">
            <a href="#" class="btn-cancel">취소</a>
            <a id="contentSubmit" href="#" class="btn-success">등록</a>
          </div>
        </div>
      </div>
      <!-- 학생 정보 수정 -->
      <div class="update-popup">
        <div class="editor">
          <div class="close">
            <a href="#" class="btn-close">닫기</a>
          </div>
          <div class="input-box">
            <label for="studentsId">학생 아이디 : </label>
            <input
              id="studentsId"
              type="text"
              placeholder="아이디 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsName">학생 이름 : </label>
            <input
              id="studentsNameUp"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsPassword">학생 비밀번호 : </label>
            <input
              id="studentsPasswordUp"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="btn-area">
            <input id="boardIdHidden" type="hidden" />
            <a id="contentUpdate" href="#" class="btn-update">수정</a>
            <a id="contentDelete" href="#" class="btn-delete">삭제</a>
          </div>
        </div>
      </div>
      <div class="navigation">
        <ul>
          <li>
            <a href="#">
              <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
              <span class="title">DW Board</span>
            </a>
          </li>
          <li>
            <a href="/게시판_프론트/board/index.html">
              <span class="icon"
                ><ion-icon name="home-outline"></ion-icon
              ></span>
              <span class="title">Dashboard</span>
            </a>
          </li>
          <li>
            <a href="#">
              <span class="icon"
                ><ion-icon name="person-outline"></ion-icon
              ></span>
              <span class="title">Students</span>
            </a>
          </li>
          <li>
            <a href="/게시판_프론트/board/logs.html">
              <span class="icon"
                ><ion-icon name="lock-closed-outline"></ion-icon
              ></span>
              <span class="title">logs</span>
            </a>
          </li>
          <li>
            <a href="#">
              <span class="icon"
                ><ion-icon name="log-out-outline"></ion-icon
              ></span>
              <span class="title">Sign Out</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
    <!-- main -->
    <div class="main">
      <div class="topbar">
        <div class="toggle">
          <!-- toggle은 나중에 만들기 -->
          <ion-icon name="menu-outline"></ion-icon>
        </div>
        <!-- search -->
        <div class="search">
          <label>
            <input
              id="searchBar"
              type="text"
              placeholder="학생이름을 검색하세요..."
            />
            <input id="keyword" type="hidden" value="null" />
          </label>
        </div>
        <div>
          <a href="#" class="logout">Logout</a>
        </div>
      </div>
      <!-- table -->
      <div class="details">
        <div class="recentOrders">
          <div class="cardHeader">
            <h2>학생 명단</h2>
            <button class="insertBtn" id="studentsIsertBtn" type="button">
              학생 추가
            </button>
          </div>
          <table>
            <thead>
              <tr>
                <th>학생 아이디</th>
                <th>학생 이름</th>
                <th>가입 날짜</th>
              </tr>
            </thead>
            <tbody id="boardData">
              <!-- <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr>
              <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr>
              <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr> -->
            </tbody>
          </table>
          <div class="pagination">
            <a href="#">Previous</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">Next</a>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script
    type="module"
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
  ></script>
  <script
    nomodule
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
  ></script>
  <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"
  ></script>
  <script>
    let list = document.querySelectorAll(".navigation li");
    function activeLink() {
      list.forEach((item) => {
        item.classList.remove("hovered");
      });
      this.classList.add("hovered");
    }
    list.forEach((item) => {
      item.addEventListener("mouseover", activeLink);
    });
  </script>
  <script>
    getStudentsList(1, 10);
    //페이징 리스트
    function getStudentsList(pageNum, pageSize) {
      var controllUrl =
        "http://localhost:8080/api/v1/students?pageNum=" +
        pageNum +
        "&pageSize=" +
        pageSize +
        " ";
      var keyword = $("#keyword").val();
      if (keyword != "null") {
        controllUrl =
          "http://localhost:8080/api/v1/students/search/searchPage?writer=" +
          keyword +
          "&pageNum=" +
          pageNum +
          "&pageSize=" +
          pageSize +
          " ";
      }
      $.ajax({
        url: controllUrl,
        type: "GET",
        dataType: "json",
        success: function (response) {
          let html = "";
          var len = response.list.length;
          if (len > 0) {
            for (var i = 0; i < len; ++i) {
              html +=
                "<tr onclick=getBoard(" +
                response.list[i].studentsId +
                ")><td>" +
                response.list[i].studentsId +
                "</td><td>" +
                response.list[i].studentsName +
                "</td><td>" +
                response.list[i].createAt +
                "</td></tr>";
            }
            var paginationHtml = "";
            if (response.hasPreviousPage) {
              //이전 페이지가 true 라면
              paginationHtml +=
                '<a onclick="getStudentsList(' +
                (response.pageNum - 1) +
                "," +
                pageSize +
                ')"href="#">Previous</a>';
            }
            for (var i = 0; i < response.navigatepageNums.length; i++) {
              //페이지 번호 길이 만큼 for문 실행
              paginationHtml +=
                '<a id ="pageNum' +
                response.navigatepageNums[i] +
                '" onclick="getStudentsList(' +
                response.navigatepageNums[i] +
                "," +
                pageSize +
                ')" href="#">' +
                response.navigatepageNums[i] +
                "</a>";
            }
            if (response.hasNextPage) {
              //다음 페이지가 true 라면
              paginationHtml +=
                '<a onclick="getStudentsList(' +
                (response.pageNum + 1) +
                ',10)"href="#">Next</a>';
            }
            $(".pagination").children().remove();
            $(".pagination").append(paginationHtml);
            //페이지 번호에 맞게 css 수정
            $("#pageNum" + pageNum).css("background-color", "#287bff");
            $("#pageNum" + pageNum).css("color", "#fff");
          } else {
            //게시글 없음 로직 구현
            html +=
              '<tr><td colspan=6 style="text-align:center;"><h2>게시글이 없습니다.</h2></td></tr>';
          }
          $("#boardData").children().remove();
          $("#boardData").append(html);
        },
        error: function (request, status, error) {
          console.log("에러내용은 ===>" + error);
        },
      });
    } //리스트끝

    //학생서치

    //학생서치
    $("#searchBar").keyup(function (key) {
      //엔터를 누를때(keycode => 13) hello world출력
      var pageNum = 1;
      var pageSize = 10;
      var search = $("#searchBar").val().trim(); //inp에 작성한 작성자를 가져옴
      if (search != "") {
        $("#keyword").val(search);
      }
      if (key.keyCode === 13) {
        //1. input 값을 가져옴.
        var search = $("#searchBar").val(); //input에 작성한 작성자를가져옴
        if (search == "") {
          alert("검색어를 입력해주세요");
          return false;
        }

        //2. ajax호출
        $.ajax({
          url:
            "http://localhost:8080/api/v1/students/search/searchPage?writer=" +
            search +
            "&pageNum=" +
            pageNum +
            "&pageSize=" +
            pageSize +
            " ",
          type: "GET",
          dataType: "json",
          success: function (response) {
            let html = "";
            if (response.list.length > 0) {
              for (var i = 0; i < response.list.length; ++i) {
                html +=
                  "<tr onclick=getBoard(" +
                  response.list[i].studentsId +
                  ")><td>" +
                  response.list[i].studentsId +
                  "</td><td>" +
                  response.list[i].studentsName +
                  "</td><td>" +
                  response.list[i].createAt +
                  "</td></tr>";
              }
              var paginationHtml = "";
              if (response.hasPreviousPage) {
                //이전 페이지가 true 라면
                paginationHtml +=
                  '<a onclick="getStudentsList(' +
                  (response.pageNum - 1) +
                  "," +
                  pageSize +
                  ')"href="#">Previous</a>';
              }
              for (var i = 0; i < response.navigatepageNums.length; i++) {
                //페이지 번호 길이 만큼 for문 실행
                paginationHtml +=
                  '<a id ="pageNum' +
                  response.navigatepageNums[i] +
                  '" onclick="getStudentsList(' +
                  response.navigatepageNums[i] +
                  "," +
                  pageSize +
                  ')" href="#">' +
                  response.navigatepageNums[i] +
                  "</a>";
              }
              if (response.hasNextPage) {
                //다음 페이지가 true 라면
                paginationHtml +=
                  '<a onclick="getStudentsList(' +
                  (response.pageNum + 1) +
                  ',10)"href="#">Next</a>';
              }
              $(".pagination").children().remove();
              $(".pagination").append(paginationHtml);
              //페이지 번호에 맞게 css 수정
              $("#pageNum" + pageNum).css("background-color", "#287bff");
              $("#pageNum" + pageNum).css("color", "#fff");
            } else {
              //게시글 없음 로직 구현
              html +=
                '<tr><td colspan=6 style="text-align:center;"><h2>게시글이 없습니다.</h2></td></tr>';
            }
            $("#boardData").children().remove();
            $("#boardData").append(html);
          },
          error: function (request, status, error) {
            console.log("에러내용은 ===>" + error);
          },
        });
      }
    }); //서치 끝.

    //상세보기
    function getBoard(studentsId) {
      $(".update-popup").css("display", "block");
      //ajax 작성
      $.ajax({
        url: "http://localhost:8080/api/v1/students/id/" + studentsId,
        type: "GET",
        dataType: "json",
        success: function (response) {
          // 3. input에 date set해주기
          $("#studentsId").val(response.studentsId);
          $("#studentsNameUp").val(response.studentsName);
          $("#studentsPasswordUp").val(response.studentsPassword);
          $("#boardIdHidden").val(studentsId);
          getStudentsList(1, 10);
        },
      });
    }
    $(".btn-close").click(function () {
      $(".update-popup").css("display", "none");
    }); //상세보기끝

    //학생수정
    $("#contentUpdate").click(function () {
      var studentsId = $("#boardIdHidden").val(); //hidden에 숨겨둔 studentsId 가져오기.
      var studentsName = $("#studentsNameUp").val();
      var studentsPassword = $("#studentsPasswordUp").val();
      var jsonData = {
        studentsName: studentsName,
        studentsPassword: studentsPassword,
      };
      $.ajax({
        url: "http://localhost:8080/api/v1/students/id/" + studentsId,
        type: "PATCH",
        contentType: "application/json", //서버에 json타입으로 요청
        dataType: "json", //서버결과 json으로요청
        data: JSON.stringify(jsonData),
        success: function (response) {
          if (response > 0) {
            alert("수정 완료");
            $(".update-popup").css("display", "none");
            $("#boardData").children().remove();
            getStudentsList(1, 10);
          }
        },
      });
    }); //수정끝

    //학생삭제
    $("#contentDelete").click(function () {
      if (confirm("정말로 삭제하시겠습니까?")) {
        var studentsId = $("#boardIdHidden").val();
        var studentsName = $("#studentsNameUp").val();
        var studentsPassword = $("#studentsPasswordUp").val();
        var jsonData = {
          studentsName: studentsName,
          studentsPassword: studentsPassword,
        };
        //3. AJAX
        $.ajax({
          url: "http://localhost:8080/api/v1/students/id/" + studentsId,
          type: "DELETE",
          contentType: "application/json", //서버에 json타입으로 요청
          dataType: "json", //서버결과 json으로요청
          data: JSON.stringify(jsonData),
          success: function (response) {
            if (response > 0) {
              $(".update-popup").css("display", "none");
              $("#boardData").children().remove();
              getStudentsList(1, 10);
            }
          },
        });
      }
    }); //삭제끝

    $("#studentsIsertBtn").click(function () {
      $(".student-popup").css("display", "block");
    });
    $(".btn-cancel").click(function () {
      $(".student-popup").css("display", "none");
    });

    //학생추가
    $("#contentSubmit").click(function () {
      if (confirm("학생을 추가하시겠습니까??")) {
        var studentsName = $("#studentsName").val();
        var studentsPassword = $("#studentsPassword").val();

        if (studentsName == "") {
          alert("학생 이름을 입력 해주세요");
          $("studentsName").focus();
          return false;
        }
        if (studentsPassword == "") {
          alert("비밀번호를 입력 해주세요");
          $("#studentsPassword").focus();
          return false;
        }

        var jsonData = {
          studentsName: studentsName,
          studentsPassword: studentsPassword,
        };

        $.ajax({
          url: "http://localhost:8080/api/v1/students",
          type: "POST",
          contentType: "application/json", //서버에 json타입으로 요청
          dataType: "json", //서버결과 json으로요청
          data: JSON.stringify(jsonData),
          success: function (reponse) {
            if (reponse > 0) {
              //작성 화면 감추기
              $(".student-popup").css("display", "none");
              //내가 작성한 글 지우기
              $("#studentsName").val("");
              $("#studentsPassword").val("");

              $("#boardData").children().remove();
              getStudentsList(1, 10);
            }
          }, //success끝
        }); //ajax끝
      } //if끝
    }); //추가끝
  </script>
</html>
