import React from 'react';
import './Common.css' 

const CardTitle = (props) => {
  return (
    <div>
      <h1>{props.title}</h1>
    </div>
  ) 
};

const EmailInput =() =>{
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
            <input id="input" type="email" placeholder="이메일"/>
          </div>
            <input id="input" type="text" placeholder="인증번호" />
        </>
    )
}

const InputBox=({children})=>{
    return(
        <>
            <div class="input_box">
                {children}
            </div>
        </>
    )
}

const SubmitButton=()=>{
  return(
    <button
      type="submit"
              className="submit_button"
            >
              제출하기
        
    </button>
  )
}

const Scene =(props) =>{
  return (
    <div id="background_gradient">
      <main id="signup_box">
          <div id>
            <CardTitle title={props.title} />
              <InputBox>
                <EmailInput />
              </InputBox>

              <InputBox>
                <p>비밀번호<br/> </p>
                <input id="input" type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <input id="input" type="password" placeholder="비밀번호 확인"/>
              </InputBox>
              <div class="input_box">
                <p>이름<br/> </p>
                <input id="input" type="text"/>
              </div>

              <div class="input_box">
                <p>주소<br/> </p>
                <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
                <input id="input" type="text" placeholder="상세주소"/>
              </div>
          </div>
          <SubmitButton/>
      </main>
    </div>
  );
};

export default Scene;
