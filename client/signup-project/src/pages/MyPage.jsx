import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import { useLocation } from 'react-router-dom';

export default function MyPage() {
    const location = useLocation();
    const userInfo = location.state?.userInfo;

    useEffect(() => {
        //로그인 상태확인하는 api 호출 ->401에러면 권한 없으므로 권한없음 페이지로 이동
        const handleCheckLogin = async (e) => {
            e.preventDefault()
            try {
                const response = await API.get(
                    '/api/v1/sessions/current', {
                })
                console.log('로그인 상태를 확인합니다');
            } catch (error) {
                if (error.response && error.response.status === 401) {
                    console.log(error.response.data);
                    navigate('/Unauthorized');
                }
                else console.error('handleCheckLogin에서 api 연결 실패:', error.message);
            }
        }
    }, [])


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
                <SubmitButton text="로그아웃" link='/login' isActive={true} />
            </main>
        </div>
    )
}
