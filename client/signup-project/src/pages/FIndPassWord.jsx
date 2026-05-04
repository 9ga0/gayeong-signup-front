
import React from "react";
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';

function FindPassWord(){
  return (
    <div id="background_gradient">
      <main id="card_box" >
            <CardTitle title="비밀번호 찾기" />
            <div id="input_box">
              <div id="input_wrap">
                <EmailBox/> 
              </div>
            </div>
            <SubmitButton text="제출하기" link='/' />

      </main>
    </div>
  )
};

export default FindPassWord
