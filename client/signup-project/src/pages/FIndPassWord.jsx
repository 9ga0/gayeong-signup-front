
import CardTitle from "../components/common/CardTitle"
import EmailBox from "../components/common/EmailBox"
import SubmitButton from "../components/common/SubmitButton"
import '../components/common/Common.css';
import { useState } from "react";

export default function FindPassword() {
  const [email, setEmail] = useState('');
  const [isCorrect, setIsCorrect] = useState(false);//인증번호 일치여부. 일치하면 제출버튼 가능

  
  return (
    <div className="background-gradient">
      <main className="card-box2">
        <CardTitle title="비밀번호 찾기" />

        <EmailBox ignoreEmailCheck={true} setIsCorrect={setIsCorrect} onSetEmail={(e) => { setEmail(e.target.value) }} />

        {isCorrect ?
          <SubmitButton text="제출하기"
            link='/change-password' email={email} />
          : //console.log('아이디 인증을 완료해주세요');를 어디다 출력해야할까.?
          <SubmitButton text="제출하기"
            context={email} email={email} />
            }

      </main>
    </div>
  )
};

