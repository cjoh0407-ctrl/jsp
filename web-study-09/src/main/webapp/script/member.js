function loginCheck() {
   if (document.frm.userid.value.length == 0) {
      alert("아이디를 써주세요");
      frm.userid.focus();
      return false;
   }
   if (document.frm.pwd.value == "") {
      alert("암호는 반드시 입력해야 합니다.");
      frm.pwd.focus();
      return false;
   }
   return true;
}

function idCheck() {
   if (document.frm.userid.value == "") {
      alert('아이디를 입력하여 주십시오.');
      document.formm.userid.focus();
      return;
   }
   var url = "idCheck.do?userid=" + document.frm.userid.value;
   window.open(url, "_blank_1",
               "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok(userid) {
   opener.frm.userid.value = document.frm.userid.value;
   opener.frm.reid.value = document.frm.userid.value;
   self.close();
}
// 열고 싶은 페이지의 주소 (URL)
	// 새 창의 이름(식별자). 같은 이름이면 같은 창을 재사용
	// 브라우저 도구 모음(툴바) 숨기기
	// 메뉴바 숨기기
	// 스크롤바는 보이도록 설정
	// 창 크기 조절 불가능
	// 새 창의 너비 450px
	// 새 창의 높이 200px

function joinCheck() {
   if (document.frm.name.value.length == 0) {
      alert("이름을 써주세요.");
      frm.name.focus();
      return false;
   }
   if (document.frm.userid.value.length == 0) {
      alert("아이디를 써주세요");
      frm.userid.focus();
      return false;
   }
   if (document.frm.userid.value.length < 4) {
      alert("아이디는 4글자이상이어야 합니다.");
      frm.userid.focus();
      return false;
   }
   if (document.frm.pwd.value == "") {
      alert("암호는 반드시 입력해야 합니다.");
      frm.pwd.focus();
      return false;
   }
   if (document.frm.pwd.value != document.frm.pwd_check.value) {
      alert("암호가 일치하지 않습니다.");
      frm.pwd.focus();
      return false;
   }
   if (document.frm.reid.value.length == 0) {
      alert("중복 체크를 하지 않았습니다.");
      frm.userid.focus();
      return false;
   }
   return true;
}