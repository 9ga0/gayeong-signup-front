import React from 'react';
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from "../components/common/SubmitButton"
import "../styles/Modal.css"
import '../components/common/Common.css';
import { useState } from "react";
import Modal from './AddressModal';
import API from '../services/API';

export default function SignUp() {
  //props로 전달..
  const [openModal, setOpenModal] = useState(false);
  const openModalHandler = () => {
    setOpenModal(!openModal);
  };
  const [isMatch, setIsMatch] = useState(false); //비밀번호 입력 및 통과했는지
  const [isCorrect, setIsCorrect] = useState(false);//인증번호 일치여부. 일치하면 제출버튼 가능
  const [ableToSubmit, setAbleToSubmit] = useState(false);
  const [isExistEmail, setIsExistEmail] = useState(false); //이미 존재하는 이메일인지 유무
  const [registerParam, setRegisterParam] = useState({
    email: '',
    password: '',
    userName: '',
    streetAddress: '',
    detailAddress: '',
  });

  const inputChange = (e) => {
    const { name, value } = e.target;
    setRegisterParam((prev) => ({
      ...prev,
      [name]: value
    }));
    console.log(registerParam);
    //함수가 끝나고 랜더링되므로 한박자 늦게 저장된것처럼 보임.
  }
  const setEmailHandler = (value) => {
    setRegisterParam((prev) => ({
      ...prev,
      email: value
    }));
    console.log(registerParam);
  } //((e 객체 반환으로 고치기))
  const setPasswordHandler = (value) => {
    setRegisterParam((prev) => ({
      ...prev,
      password: value
    }));
    console.log(registerParam);
  }
  const setAddressHandler = (address) => {
    setRegisterParam((prev) => ({
      ...prev,
      streetAddress: address
    }));
  }

  //모든 입력값이 ''이 아니라 값으로 채워져있는지,
  //이메일은 인증번호가 인증되었는지, 비밀번호는 일치하는지, 이름과 주소 다 입력되어있는지 <-구현해야함
  //점검하는 함수
  const checkUserInfo = () => { ///더 디테일한 에러판단 적용 필요!!!
    if (registerParam.email
      && registerParam.password
      && registerParam.userName
      && registerParam.streetAddress
      && registerParam.detailAddress
      && isCorrect
      && isMatch) { //+비밀번호 일치여부
      setAbleToSubmit(true);
      return true;
    }
    return false;
  }

  //회원가입 정보 post api보내기. submitButton클릭시 동작
  const signupUser = async (e) => {
    e.preventDefault();

    // 입력 값의 형태가 올바른지 판별 
    if (!checkUserInfo()) {
      console.log('회원가입 실패: 조건 충족 안 함');
      //'~을 입력하세요' 경고문구 출력.
      setAbleToSubmit(false);
      return; //새로고침? 값 초기화?
    }
    try {
      const response = await API.post('/api/v1/auth/signup', {
        email: registerParam.email,
        password: registerParam.password,
        username: registerParam.userName,
        streetAddress: registerParam.streetAddress,
        detailAddress: registerParam.detailAddress,
      });
      //response.status === 200) 
      setAbleToSubmit(true);
      console.log('올바른 입력으로 회원가입되었습니다.');
    }
    catch (error) {
      if (error.response && error.response.status === 409) {
        console.log(response.data);//이미 사용 중인 이메일입니다
        setErrors({ ...errors, ['email']: '이미 사용 중인 이메일입니다.' });
        setIsExistEmail(false)
        setAbleToSubmit(false);
      }
      console.error('signupUser에서 api 연결 실패:', error.message);
    }
  };

  return (
    <div className="background-gradient">
      <form onSubmit={signupUser}>
        <main className="card-box">
          <CardTitle title="회원가입" />

          <div className="gap-24px">
            <EmailBox
              name='email'
              value={registerParam.email}
              onSetEmail={inputChange}
              setIsCorrect={setIsCorrect}
              isExistEmail={isExistEmail} />

            <div className="input-wrap">
              <PasswordBox
                name='password'
                onSetPassword={inputChange}
                setIsMatch={setIsMatch}
                value={registerParam.password} />
            </div>

            <div className="input-wrap">
              <div className="sub-title" >이름 </div>
              <div className="input-container">
                <input name='userName' className="input2"
                  type="text" onChange={inputChange}
                  value={registerParam.userName} />
              </div>
            </div>

            <div className="input-wrap">
              <div className="sub-title">주소 </div>
              <div className="input-container">
                <input
                  name='streetAddress'
                  className="input2"
                  type="text"
                  onClick={openModalHandler}
                  placeholder="클릭하여 주소 검색"
                  onChange={inputChange}
                  value={registerParam.streetAddress}
                />
              </div>
              {openModal ? <Modal openModal={openModal} setOpenModal={setOpenModal} setDataAddress={setAddressHandler} /> : null}
              <div className="input-container">
                <input name='detailAddress'
                  className="input2"
                  type="text"
                  placeholder="상세주소"
                  onChange={inputChange}
                  value={registerParam.detailAddress} />
              </div>
            </div>
          </div>
          <SubmitButton text="제출하기" link='/success'
            context="회원가입 완료" isActive={ableToSubmit} onSubmit={signupUser} />
        </main >
      </form>
    </div >

  );
};
