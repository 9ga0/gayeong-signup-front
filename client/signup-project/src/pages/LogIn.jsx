//import reactLogo from './assets/react.svg' //이미지 불러오기
import React from "react";
import '../styles/Login.css'; //css 불러오기
import { Link } from "react-router-dom";
import Email from '/src/assets/Email.svg';
import Password from '/src/assets/Password.svg';
import FindPassWord from "./FIndPassWord";

function LogIn(){

  return (
    <>
      <div id="background_gradient">  
        <main id="signup_box"> 
          <h1>En# SignUp!!</h1> 

          <form className="input_box">
            <div class="input_container">
              <input id="input2" type="email" placeholder="E-mail"/>
              <img src={Email} className="input_img"></img>
            </div>

            <div class="input_container">
              <input id="input2" type="password" placeholder="Password"/>
              <img src={Password} className="input_img"></img>
            </div>

            <div id="line_box">
                <input id="checkbox" type="checkbox"/>아이디 저장
                <Link id="" to="/find-password">비밀번호 찾기</Link>
            </div>

          </form>

            
          <button 
            type="button"
            className="submit_button"
            >Login</button>

          계정이 없으신가요?
           <Link to="/sign-up">회원가입</Link>
        </main>
      </div>
    </>
  )
}

export default LogIn
