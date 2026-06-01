import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import axios from 'axios';
import API from '../services/API';
import { useNavigate, useLocation } from 'react-router-dom';
import { checkLogin } from '../services/CheckLoginApi';

export default function MyPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const userInfo = location.state?.userInfo || {};

    // useEffect(() => {
    //     //로그인 상태확인하는 api 호출 ->401에러면 권한 없으므로 권한없음 페이지로 이동
    //     console.log('로그인상태 확인합니다.')

    //     const verifyAdmin = async () => {
    //         const isAuthrized = await checkLogin();

    //         if (!isAuthrized) {
    //             console.log('권한없음 페이지로 이동합니다.')
    //             navigate('/unauthorized');
    //             return;
    //         }

    //         const userInfo = await getMyInfo(email); //유저정보 api 연결

    //     }
    //     verifyAdmin();

    // }, [])

    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="내정보" />
                <div >
                    <p className='sub-title'>이름: {userInfo.username||"없음"}</p>
                    <p className='sub-title'>이메일: {userInfo.email||"없음"}</p>
                    <p className='sub-title'>주소: {userInfo.streetAddress ||"없음"}</p>
                    <p className='sub-title'>상세주소: {userInfo.detailAddress ||"없음"}</p>
                </div>
                <SubmitButton text="로그아웃" link='/login' isActive={true} />
            </main>
        </div>
    )
}
