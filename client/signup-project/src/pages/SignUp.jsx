import React from 'react';
import CardTitle from "../components/common/CardTitle"
//import InputBox from "../components/common/Common"
import EmailBox from "../components/common/EmailBox"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from "../components/common/SubmitButton"
import "../styles/Modal.css"
import '../components/common/Common.css';
import { useState } from "react";
import Modal from './AddressModal';

export default function SignUp() {
  const [openModal, setOpenModal] = useState(false);
  const openModalHandler = () => {
    setOpenModal(!openModal);
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
                className="input2"
                className="modal-open"
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
