
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';
import { useState } from "react";

export default function FindPassword() {
  const [email,setEmail]=useState('');
  return (
    <div className="background-gradient">
      <main className="card-box2">
        <CardTitle title="비밀번호 찾기" />

        <EmailBox ignoreEmailCheck={true} onSetEmail={(e)=>{setEmail(e.target.value)}} />
        {/*찾기페이지에서 입력한 email가져와서 재설정페이지에 저장시도하다가 포기*/}

        <SubmitButton text="제출하기" link='/change-password' email={email} />
      </main>
    </div>
  )
};

