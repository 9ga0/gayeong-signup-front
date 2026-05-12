import React from 'react';
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from "../components/common/SubmitButton"
import "../styles/Modal.css"
import '../components/common/Common.css';
import { useState } from "react";
import Modal from './AddressModal';
import  API  from '../services/API';

export default function SignUp() {
  //props로 전달..
  const [openModal, setOpenModal] = useState(false);
  const openModalHandler = () => {
    setOpenModal(!openModal);
  };
  const [profile, setProfile] = useState('');

  const [info, setInfo] = useState({
    email: '',
    password: '',
    name: '',
    address: '',
  });
  const handleButton = () => {
    /* 입력 값의 형태가 올바른지 판별 */
    if (checkUserInfo(info.nickName, info.email, info.pw, setErrorMessage))
      return;

    /* 데이터 전송 */ 
    API.post('/user/create', {
      nickName: info.nickName,
      email: info.email,
      pw: info.pw,
      profilePicture: profile
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
      <main className="card-box">
        <CardTitle title="회원가입" />
        <div className="gap-24px">
          <EmailBox />

          <div className="input-wrap">
            <PasswordBox />
          </div>

          <div className="input-wrap">
            <div className="sub-title">이름 </div>
            <div className="input-container">
              <input className="input2" type="text" />
            </div>
          </div>

          <div className="input-wrap">
            <div className="sub-title">주소 </div>
            <div className="input-container">
              <div
                className="input2 modal-open"
                type="address"
                onClick={openModalHandler}
              >
                클릭하여 주소 검색
              </div>
            </div>
            {openModal ? <Modal openModal={openModal} /> : null}
            <div className="input-container">
              <input className="input2" type="text" placeholder="상세주소" />
            </div>
          </div>
        </div>

        <SubmitButton text="제출하기" link='/success' context="회원가입 완료" />
      </main >
    </div >

  );
};
