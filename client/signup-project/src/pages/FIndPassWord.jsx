
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';
import { useState } from "react";


function FindPassWord(){
  const email='';
  return (
    <div className="background_gradient">
      <main className="card_box" >
            <CardTitle title="비밀번호 찾기" />

            <div className="input_box" >
              <div className="input_wrap">
                <EmailBox email={email}/> 
                {/*찾기페이지에서 입력한 email가져와서 재설정페이지에 저장시도하다가 포기*/}
              </div>
            </div>

            <SubmitButton text="제출하기" link='/change-password' email={email} />
      </main>
    </div>
  )
};

export default FindPassWord
