import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import { useLocation } from 'react-router-dom';

//'/admin-page'
export default function AdminPage() {
    const location = useLocation();
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="관리자 페이지" />
                <div className='gap-16px'>
                    {/* 현재 링크페이지 notFound라고 뜸 */}
                    <SubmitButton text="전체 유저 조회" link='/inquiry-all-user' isActive={true} />
                    <SubmitButton text="특정 유저 삭제" link='/delete-user' isActive={true} />
                    <SubmitButton text="이메일 인증 기록 전체 삭제" link='/delete-email-verification-history' isActive={true} />
                </div>
                <SubmitButton text="로그아웃" link='/login' isActive={true} />

            </main>
        </div>
    )
}
