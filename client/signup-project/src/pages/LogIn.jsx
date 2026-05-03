//import reactLogo from './assets/react.svg' //이미지 불러오기
import React from "react";
import '../styles/Login.css'; //css 불러오기


function LogIn(){
  return (
    <>
      <div id="background_gradient">  
        <main id="signup_box"> 
          <h1>En# SignUp!!</h1> 

          <form class="input_box">
            {/* className */}
            <input id="input" type="email" placeholder="E-mail"/>
            <input id="input" type="password" placeholder="Password"/>
            <div id="line_box">
              <input id="checkbox" type="checkbox"/>아이디 저장
              <p>비밀번호 찾기</p> 
              {/* 이동ul */}
              {/* 묶는박스 추가 */}
            </div>

          </form>

            
          <button 
            type="button"
            className="submit_button"
            >Login</button>

          <p>계정이 없으신가요?</p>
          {/* <ul>
            <Link to="/SignUpPage"><li>회원가입</li></Link>
          </ul> */}
        </main>
      </div>
    </>
  )
}

export default LogIn
