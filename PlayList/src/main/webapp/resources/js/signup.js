// 유효성 검사 객체
const checkObj = { 
    "inputNm" : false, // 닉네임 
    "inputId" : false, // 아이디
    "inputPw" : false, // 비밀번호
    "inputPwc" : false // 비번확인
};

// 닉네임 유효성 검사
const inputNm = document.getElementById("inputNm");

inputNm.addEventListener("change", function() {

    const regExp = /[가-힣]{2,5}/;

    const nmMessage = document.getElementById("nmMessage");

    if(regExp.test(this.value)) {
        nmMessage.innerText = "정상입력";
        this.style.backgroundColor = "green";
        this.style.color = "white";
        checkObj.inputNm = true;
    } else {
        nmMessage.innerText = "2글자에서 5글자 사이 한글만 입력하세요";
        this.style.backgroundColor = "red";
        this.style.color = "white";
        checkObj.inputNm = false;
    }
});

// 아이디 유효성 검사
const inputId = document.getElementById("inputId");

inputId.addEventListener("change", function() {

    const regExp = /^[a-z][\w!@#$%^&*_-]{5,13}/;

    if(regExp.test(this.value)) {
        this.style.backgroundColor = "green";
        this.style.color = "white";
        checkObj.inputId = true;
    } else {
        this.style.backgroundColor = "red";
        this.style.color = "white";
        checkObj.inputId = false;
    }
});

// 비밀번호 유효성 검사
const inputPw = document.getElementById("inputPw");
const inputPwc = document.getElementById("inputPwc");

inputPwc.addEventListener("keyup", function() {
    
    if(inputPw.value.length == 0) {
        this.value = "";
        alert("비밀번호를 먼저 입력해주세요");
        inputPw.focus();
        checkObj.inputPw = false;
    }
});

const pwMessage = document.getElementById("pwMessage");

inputPw.addEventListener("keyup", function() {

    if( (inputPw.value == inputPwc.value) && inputPw.value.length != 0) {
        pwMessage.innerText = "비밀번호 일치";
        pwMessage.style.color = "green";
        checkObj.inputPw = true;
        checkObj.inputPwc = true;
    } else {
        pwMessage.innerText = "비밀번호 불일치";
        pwMessage.style.color = "red";
        checkObj.inputPwc = false;
    }
});

inputPwc.addEventListener("keyup", function() {

    if( (inputPw.value == inputPwc.value) && inputPw.value.length != 0) {
        pwMessage.innerText = "비밀번호 일치";
        pwMessage.style.color = "green";
        checkObj.inputPw = true;
        checkObj.inputPwc = true;
    } else {
        pwMessage.innerText = "비밀번호 불일치";
        pwMessage.style.color = "red";
        checkObj.inputPwc = false;
    }
});