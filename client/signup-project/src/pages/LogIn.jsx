//import reactLogo from './assets/react.svg' //이미지 불러오기
import React, { useEffect } from "react";
import { useState } from "react";
import '../styles/Login.css';
import { Link, useNavigate } from "react-router-dom";
import Email from '/src/assets/Email.svg';
import Password from '/src/assets/Password.svg';
import SubmitButton from "../components/common/SubmitButton.jsx";
import PasswordInput from "../components/common/PasswordInput.jsx";
import CardTitle from "../components/common/CardTitle.jsx";
import API from '../services/API.jsx';
import '../components/common/Common.css';
import { getMyInfo } from "../services/MyInfoApi.jsx";

export default function LogIn() {
  const [saveIDFlag, setSaveIDFlag] = useState(false);
  const LS_KEY_ID = "LS_KEY_ID";
  const LS_KEY_SAVE_ID_FLAG = "LS_KEY_SAVE_ID_FLAG";
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isAbleToLogin, setIsAbleToLogin] = useState(false);
  const navigate = useNavigate();

  //로그인 post api보내기. submitButton클릭시 동작
  const loginUser = async (e) => {
    e.preventDefault();

    // 입력 값이 비어있는지만 판별 
    if (!email || !password) {
      console.log('회원가입 실패: 이메일 또는 패스워드가 비어있음');
      !email ? setError('이메일을 입력하세요.') : setError('비밀번호를 입력하세요.');
      //'~을 입력하세요' 경고문구 출력.
      setIsAbleToLogin(false);
      return;
    }
    if (saveIDFlag) {
      localStorage.setItem(LS_KEY_ID, email);
    }

    //console.log(email, ':', password);
    try {
      const response = await API.post('/api/v1/sessions'
        , {
          email,
          password
        }
      )
      setIsAbleToLogin(true);
      console.log('올바른 입력으로 로그인되었습니다.');

      console.log(response.data);
      if (response.data.role === "ROLE_ADMIN") {
        navigate('/admin-page');
      } else {
        //const userInfo = await getMyInfo(email); //유저정보 api 연결
        navigate('/my-page', {
          state: { userInfo: response.data }
        });
      }

    } catch (error) {
      console.log(status);
      if (error.response && error.response.status === 401) {
        setError('아이디 또는 비밀번호가 올바르지 않습니다');
        setIsAbleToLogin(false);
      }
      else console.error('loginUser에서 api 연결 실패:', error.message);
    }
  }
  const changeHandler = (e) => {
    setEmail(e.target.value); //업데이트 예약-아직반영안된것이다
    setError('');
    //console.log(email, ":", password); 
    if (saveIDFlag) { localStorage.setItem(LS_KEY_ID, e.target.value); }
  }

  // 아이디 저장 체크박스 활성화
  const handleRememberId = () => {
    localStorage.setItem(LS_KEY_SAVE_ID_FLAG, !saveIDFlag);
    setSaveIDFlag(!saveIDFlag);
    if (!saveIDFlag) {
      localStorage.setItem(LS_KEY_ID, email);
      console.log("check");
    } else {
      console.log('uncheck');
    }
  };

  // 컴포넌트가 처음 마운트 될 때만 실행되도록 빈 배열을 두번째 인자로 넘김
  useEffect(() => {
    // 로컬스토리지에서 저장 활성화 버튼 여부
    let idFlag = JSON.parse(localStorage.getItem(LS_KEY_SAVE_ID_FLAG));

    if (idFlag !== null) setSaveIDFlag(idFlag);

    // 저장이 안됨
    if (idFlag === false) localStorage.setItem(LS_KEY_ID, "");

    // storage 에서 꺼낸 값 id 값
    let data = localStorage.getItem(LS_KEY_ID);

    // 저장되어있는 값이 있음
    if (data !== null) setEmail(data);
  }, []);

  return (
    <>
      <div className="background-gradient">
        <form onSubmit={loginUser}>
          <main className="card-box">
            <CardTitle title="En# SignUp!!" />

            <div className="gap-16px" onSubmit={loginUser}>
              <div className="input-container">
                <input className="input2" value={email}
                  onChange={changeHandler}
                  type="email" placeholder="E-mail" />
                <img src={Email} className="input-img"></img>
              </div>
              <PasswordInput name='pw' value={password}
                setPassword={setPassword}
                setError={setError}
                error={error}
                placeholder="Password" img={Password} />

              {error ? <p className='error' style={{ textAlign: 'left', width: '100%', maginLeft: 0 }}>{error}</p> : null}
              {/* '이메일 주소가 정확한지 확인해 주세요.' 로그인 실패(이메일, 비밀번호 불일치) 시 문구 출력 */}
              <div className="line-box">
                <div className="row-align">
                  <input className="checkbox"
                    type="checkbox"
                    onChange={handleRememberId}
                    checked={saveIDFlag}
                  />
                  <div className="safe-id-text">아이디 저장</div>
                </div>
                <Link className="find-password-link-text" to="/find-password">비밀번호 찾기</Link>
              </div>

            </div>

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
        </form>
      </div>
    </>
  )
}

