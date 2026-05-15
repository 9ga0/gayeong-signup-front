import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import { useLocation } from 'react-router-dom';

export default function MyPage() {
    const location = useLocation();
    const userInfo = location.state?.userInfo;
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="내정보" />
                <div >
                    <p className='sub-title'>이름: {userInfo.username}</p>
                    <p className='sub-title'>이메일: {userInfo.email}</p>
                    <p className='sub-title'>주소: {userInfo.streetAddress}</p>
                    <p className='sub-title'>상세주소: {userInfo.detailAddress}</p>
                </div>
                <SubmitButton text="로그아웃" link='/' isActive={true} />

            </main>
        </div>
    )
}
