
import Check from "../assets/Check.svg"
import React from 'react';
import '../components/common/Common.css';
import CardTitle from "../components/common/CardTitle";
import SubmitButton from "../components/common/SubmitButton";
import { useLocation } from 'react-router-dom';

export default function Success(){
    const location = useLocation();
    const data= {...location.state};

  return (
    <div className="background_gradient">
      <main className="card_box" >
            <CardTitle title='안내' />

            <div style={{ display: 'block', gap: '8px' }} >
              <img className='check_image' src={Check}/>
              <p style={{ textAlign: 'center' }}>{data.context}</p> {/*회원가입 완료 or 비밀번호 재설정 완료*/}
            </div>

            <SubmitButton text="로그인 하기" link='/' />
      </main>
    </div>
  )
};