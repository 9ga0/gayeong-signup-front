import React from 'react';
import CardTitle from "../components/common/CardTitle"
//import InputBox from "../components/common/Common"
import EmailBox from "../components/common/EmailBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';
import { useState } from "react";

function InputBox(children){
    return(
      <div id="input_box">
        {children}
      </div>
    )
}
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
  const PASSWORD_REGEX = /^[A-Za-z0-9]{8,16}$/;

  return (
    <div id="background_gradient">
      <main id="card_box">
            <CardTitle title="회원가입" />
            <div id="input_box">
              <div id="input_wrap">
                <EmailBox/>
              </div>

              <div id="input_wrap">
                <p>비밀번호<br/> </p>
                <input id="input" 
                  value={password}
                  type={visible ? "text": "password"}
                  onChange={(e) => setPassword(e.target.value)}
                  type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <input id="input" type="password" placeholder="비밀번호 확인"/>
                <img className="visible_btn" src="client\signup-project\src\assets\eye-lock.svg" onClick={() => setVisible(!visible)}>
                  {/* {visible? <EyeOutlined/>:<EyeInvisibleOutlined/>} */}
                </img>
              </div>

              <div id="input_wrap">
                <p>이름<br/> </p>
                <input id="input" type="text"/>
              </div>

              <div id="input_wrap">
                <p>주소<br/> </p>
                <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
                <input id="input" type="text" placeholder="상세주소"/>
              </div>
            </div>
            <SubmitButton text="제출하기" link='/' />
      </main>
    </div>
  );
};
