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
import API from '../services/API';

export default function LogIn() {
  const [savedID, setSavedID] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  return (
    <>
      <div className="background-gradient">
        <main className="card-box">
          <CardTitle title="En# SignUp!!" />

          <form className="gap-16px">
            <div className="input-container">
              <input className="input2" value={email} onChange={(e) => { setEmail(e.target.value) }} type="email" placeholder="E-mail" />
              <img src={Email} className="input-img"></img>
            </div>
            <PasswordInput name='pw' value={password}
              onChange={(e) => { setPassword(e.target.value) }}
              setError={setError}
              error={error}
              isLogin={true}
              placeholder="Password" img={Password} />

            {/* {errors && <div className='error'>{errors}</div>} //common.css 불러와야할수도*/}
            {/* '이메일 주소가 정확한지 확인해 주세요.' 로그인 실패(이메일, 비밀번호 불일치) 시 문구 출력 */}
            <div className="line-box">
              <div className="row-align">
                <input className="checkbox" onChange={() => { setSavedID(email) }} type="checkbox" />
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

