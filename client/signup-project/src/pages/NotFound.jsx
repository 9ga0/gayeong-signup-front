import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import { useLocation } from 'react-router-dom';

//페이지 못찾을때 띄울 페이지
export default function NotFound() {
    const location = useLocation();
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="페이지를 못 찾았습니다!" />
                <div >
                    <p className='sub-title'>ERROR ERROR ERROR</p>
                </div>
                <SubmitButton text="로그인화면으로 돌아가기" link='/login' isActive={true} />

            </main>
        </div>
    )
}