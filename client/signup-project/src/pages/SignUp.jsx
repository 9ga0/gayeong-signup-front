import React from 'react';
import CardTitle from "../components/common/CardTitle"
//import InputBox from "../components/common/Common"
import EmailBox from "../components/common/EmailBox"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';
import { useState } from "react";

const onChangePasswordHandler = (e) => { //비밀번호 입력받는 핸들러
    const { name, value } = e.target;
    if (name === 'password') {
      setPassword(value);
      passwordCheckHandler(value, confirm);
    } else {
      setConfirm(value);
      passwordCheckHandler(password, value);
    }
}

export default function SignUp() {
  const [password, setPassword] = useState("");
  const [visible, setVisible] = useState(true);
  const [isClicked, setIsClicked] = useState(false); // 클릭 여부를 state로 관리

  const PASSWORD_REGEX = /^[A-Za-z0-9]{8,16}$/;

  return (
    <div id="background_gradient">
      <main id="card_box">
            <CardTitle title="회원가입" />
            
              <div id="input_box">
                <EmailBox/>
              </div>

              <div id="input_box">
                <PasswordBox password={password} visible={visible} setPassword={setPassword}/>
              </div>

              <div id="input_box">
                <p>이름<br/> </p>
                 <div class="input_container">
                  <input id="input2" type="text"/>
                </div>
              </div>

              <div id="input_box">
                <p>주소<br/> </p>
                 <div class="input_container">
                  <input id="input2" type="address" placeholder="클릭하여 주소 검색"/>
                 </div>
                 <div class="input_container">
                  <input id="input2" type="text" placeholder="상세주소"/>
                </div>
              </div>
            
            <SubmitButton text="제출하기" link='/success' context="회원가입 완료" />
      </main>
    </div>
  );
};
