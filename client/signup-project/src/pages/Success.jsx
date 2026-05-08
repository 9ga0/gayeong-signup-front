
import Check from "../assets/Check.svg"
import React from 'react';
import '../components/common/Common.css';
import '../styles/Success.css'
import CardTitle from "../components/common/CardTitle";
import SubmitButton from "../components/common/SubmitButton";
import { useLocation } from 'react-router-dom';

export default function Success() {
  const location = useLocation();
  const data = { ...location.state };

  return (
    <div className="background-gradient">
      <main className="card-box2" >
        <CardTitle title='안내' />
        <div>
          <img className='check-image' src={Check} />
          <p className="success-context">{data.context}</p> {/*회원가입 완료 or 비밀번호 재설정 완료*/}
        </div> 
        <SubmitButton style={{fontSize:"20px"}} text="로그인 하기" link='/' />
      </main>
    </div>
  )
};