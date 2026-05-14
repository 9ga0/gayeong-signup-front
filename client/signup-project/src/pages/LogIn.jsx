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
import '../components/common/Common.css';

export default function LogIn() {
  const [savedID, setSavedID] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  //로그인 post api보내기. submitButton클릭시 동작
  const loginUser = async (e) => {
    e.preventDefault();

    // 입력 값의 형태가 올바른지 판별 
    if (!email|| !password) {
      console.log('회원가입 실패: 이메일 또는 패스워드가 비어있음');
      //'~을 입력하세요' 경고문구 출력.
      setAbleToSubmit(false);
      return; //새로고침? 값 초기화?
    }
    try {
      API.post('/api/v1/auth/login', {
        email: email,
        password: password
      })
      setAbleToSubmit(true);
      console.log('올바른 입력으로 로그인되었습니다.');
    }catch (error) {
      console.log(status);
      if (error.response && error.response.status === 400) {
        setErrors( '이메일 주소가 정확한지 확인해 주세요.');
        setIsExistEmail(false)
        setAbleToSubmit(false);
      }
      console.error('signupUser에서 api 연결 실패:', error.message);
    }
  }

  return (
    <>
      <div className="background-gradient">
        <main className="card-box">
          <CardTitle title="En# SignUp!!" />

          <form className="gap-16px" onSubmit={loginUser}>
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

            {error && <div className='error'>{error}</div>} 
            {/* '이메일 주소가 정확한지 확인해 주세요.' 로그인 실패(이메일, 비밀번호 불일치) 시 문구 출력 */}
            <div className="line-box">
              <div className="row-align">
                <input className="checkbox" type="checkbox" /> 
                {/* checked={saveIDFlag} 아이디 저장 기능 추가예정. */}
                <div className="safe-id-text">아이디 저장</div> 
              </div>
              <Link className="find-password-link-text" to="/find-password">비밀번호 찾기</Link>
            </div>

          </form>

          <div className="gap-16px">
            <SubmitButton text="Login" link='/my-page'/>  
            {/* onClick해서 로그인 제출 구현. */}

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

