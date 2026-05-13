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
  const [dataAddress, setDataAddress] = useState('');
  const state = {
    email: '',
    password: '',
    name: '',
    address: '',
    detailAddress: '',
  }

  const inputChange = (e) => {
    setState({
      [e.target.name]: e.target.value
    })
  }

  //유저 회원가입하기. submitButton클릭시 동작
  const signupUser = (e) => {
    e.preventDefault();
    //props.onCreate(state);
    
  }
  const handleClick=() =>{
    console.log(state)
  }

  const handleButton = () => { /////수정 후 사용...!!!
    // 입력 값의 형태가 올바른지 판별 
    if (checkUserInfo(info.nickName, info.email, info.pw, setErrorMessage))
      return;

    // 데이터 전송  
    API.post('/api/v1/auth/signup', {
      email: info.email,
      pw: info.pw,
    })
      .then((response) => {
        if (response.status === 200) {
          const authEmail = { email: userData.email };
        }
      })
      .catch((error) => console.log(error.response));
  };

  return (
    <div className="background-gradient">
      <form onSubmit={signupUser}>
        <main className="card-box">
          <CardTitle title="회원가입" />

          <div className="gap-24px">
            <EmailBox
              name='email'
              onChange={inputChange}
              value={state.email} />

            <div className="input-wrap">
              <PasswordBox
                name='password'
                onChange={inputChange}
                value={state.password} />
            </div>

            <div className="input-wrap">
              <div className="sub-title" >이름 </div>
              <div className="input-container">
                <input name='userName' className="input2"
                  type="text" onChange={inputChange}
                  value={state.userName} />
              </div>
            </div>

            <div className="input-wrap">
              <div className="sub-title">주소 </div>
              <div className="input-container">
                <input
                  name='detailAddress'
                  className="input2"
                  type="text"
                  value={dataAddress}
                  onClick={openModalHandler}
                  placeholder="클릭하여 주소 검색"
                  onChange={inputChange}
                  value={state.address}
                />
              </div>
              {openModal ? <Modal openModal={openModal} setOpenModal={setOpenModal} setDataAddress={setDataAddress} /> : null}
              <div className="input-container">
                <input name='detailAddress'
                  className="input2" type="text"
                  placeholder="상세주소"
                  onChange={inputChange}
                  value={state.detailAddress} />
              </div>
            </div>
          </div>
          <SubmitButton text="제출하기" link='/success'
            context="회원가입 완료" onClick={handleClick}/>
        </main >
      </form>
    </div >

  );
};
