// [제목] 
// ----30-----
// [Component-EmailBox] 이메일박스(email, number), 
// ----24-----
// 비밀번호
// [input] 8~16자 영문, 특수문자, 
// [input] 비밀번호 확인
// ----24-----
// [input] 이름
// ----24-----
// [input] 클릭하여 주소 검색
// [input] 상세주소
// ----30-----
// [제출하기]
import React from 'react';
import CardTitle from "../components/common/Common"
//import InputBox from "../components/common/Common"
//import EmailBox from "../components/common/Common"
//import SubmitButton from "../components/common/Common"
import '../components/common/Common.css';
function InputBox(children){
    return(
      <div id="input_box">
        {children}
      </div>
    )
}
function EmailBox(){
    return(
        <>
          <p>이메일<br/> </p>
          <div>
            <button
                type="button"
                className="email_button"
                >
                전송
            </button>
            <input id="input" placeholder="이메일"/>
          </div>
            <input id="input" placeholder="인증번호" />
        </>
    )
};
function SubmitButton(props){
  return(
    <button
          type="submit"
          className="submit_button"
            >
            <p>{props.title}</p>
        
    </button>
  )
} 

function SignUp() {
  return (
    <div id="background_gradient">
      <main id="card_box">
            <CardTitle title="회원가입" />
            <div id="input_box">
              <div id="input_wrap">
                <EmailBox/>
              </div>

              <div id="input_wrap">
                <p>비밀번호<br/> </p>
                <input id="input" type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <input id="input" type="password" placeholder="비밀번호 확인"/>
              </div>

              <div id="input_wrap">
                <p>이름<br/> </p>
                <input id="input" type="text"/>
              </div>

              <div id="input_wrap">
                <p>주소<br/> </p>
                <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
                <input id="input" type="text" placeholder="상세주소"/>
              </div>
            </div>
            <SubmitButton title="제출하기" />
      </main>
    </div>
  );
};

export default SignUp
