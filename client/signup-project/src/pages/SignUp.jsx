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

  return (
    <div id="background_gradient">
      <main id="card_box">
        <CardTitle title="회원가입" />

        <div id="input_box">
          <EmailBox />
        </div>

        <div id="input_box">
          <PasswordBox />
        </div>

        <div id="input_box">
          <p>이름<br /> </p>
          <div class="input_container">
            <input id="input2" type="text" />
          </div>
        </div>

        <div id="input_box">
          <p>주소<br /> </p>
          <div class="input_container">
            <button
              id="input2"
              className= "modal_open"
              type="address"
              onclick={() => { setOpenModal(true) }}
            >
              클릭하여 주소 검색
            </button>
          </div>
          <Modal />
          {openModal ? <Modal /> : null}
          <div class="input_container">
            <input id="input2" type="text" placeholder="상세주소" />
          </div>
        </div>

        <SubmitButton text="제출하기" link='/success' context="회원가입 완료" />
      </main >
    </div >
    
  );
};
