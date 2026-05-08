//import reactLogo from './assets/react.svg' //이미지 불러오기
import React from "react";
import { useState } from "react";
import '../styles/Login.css'; 
import { Link } from "react-router-dom";
import Email from '/src/assets/Email.svg';
import Password from '/src/assets/Password.svg';
import FindPassWord from "./FIndPassWord";
import SubmitButton from "../components/common/SubmitButton";
import PasswordInput from "../components/common/PasswordInput";

function LogIn() {
  const [isWrited, setIsWrited] = useState(true);
  // handleChange가 인식이 안돼서 자물쇠<->눈 입력박스 변경이 되지 않는다.
  // 따라서 일단 기본 입력을 눈모양 보이는 걸로 설정.
  const handleChange = (e) => {
    if (isWrited) {
      setIsWrited(false);
    }
    else {
      setIsWrited(true);
    }
  }

  return (
    <>
      <div id="background_gradient">
        <main id="signup_box">
          <h1>En# SignUp!!</h1>

          <form className="input_box">
            <div class="input_container">
              <input id="input2" type="email" placeholder="E-mail" />
              <img src={Email} className="input_img"></img>
            </div>

            {isWrited ?
              <PasswordInput name='pw' onChange={handleChange} placeholder="Password" />
              :
              <div class="input_container">
                <input id="input2" type="password" on placeholder="Password" />
                <img src={Password} onChange={handleChange} className="input_img"></img>
              </div>
            }

            <div id="line_box">
              <label>
                <input id="checkbox" type="checkbox" />
                <p>아이디 저장</p>
              </label>
              <Link id="find-password-link-text" to="/find-password">비밀번호 찾기</Link>
            </div>

          </form>


          <SubmitButton text="Login" />
          <label>
            계정이 없으신가요?
            <Link
              id="signup-link-text"
              to="/sign-up"
              style={{ color: 'rgba(51, 51, 51, 1)' }}>
              회원가입
            </Link>
          </label>
        </main>
      </div>
    </>
  )
}

export default LogIn
