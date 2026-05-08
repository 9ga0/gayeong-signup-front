//import reactLogo from './assets/react.svg' //이미지 불러오기
import React from "react";
import { useState } from "react";
import '../styles/Login.css';
import { Link } from "react-router-dom";
import Email from '/src/assets/Email.svg';
import Password from '/src/assets/Password.svg';
import SubmitButton from "../components/common/SubmitButton";
import PasswordInput from "../components/common/PasswordInput";
import CardTitle from "../components/common/CardTitle";

export default function LogIn() {
  const [isWrited, setIsWrited] = useState(true);
  // handleChange가 인식이 안돼서 자물쇠<->눈 입력박스 변경이 되지 않는다.
  // 따라서 일단 기본 입력을 눈모양 보이는 걸로 설정해 놓은 상태.
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
      <div className="background-gradient">
        <main className="card-box">
          <CardTitle title="En# SignUp!!" />

          <form className="gap-16px">
            <div className="input-container">
              <input className="input2" type="email" placeholder="E-mail" />
              <img src={Email} className="input-img"></img>
            </div>

            {isWrited ?
              <PasswordInput name='pw' onChange={handleChange} placeholder="Password" />
              :
              <div className="input-container">
                <input className="input2" type="password" on placeholder="Password" />
                <img src={Password} onChange={handleChange} className="input-img"></img>
              </div>
            }

            <div className="line-box">
              <div className="row-align">
                <input className="checkbox" type="checkbox" />
                <div className="safe-id-text">아이디 저장</div>
              </div>
              <Link className="find-password-link-text" to="/find-password">비밀번호 찾기</Link>
            </div>

          </form>

          <div className="gap-16px">
            <SubmitButton text="Login" />

            <div className="row-align">
              <div className="quest-no-account">계정이 없으신가요?</div>

              <Link
                className="signup-link-text"
                to="/sign-up"
                style={{ color: 'rgba(51, 51, 51, 1)' }}>
                회원가입
              </Link>
            </div>
          </div>
        </main>
      </div>
    </>
  )
}

