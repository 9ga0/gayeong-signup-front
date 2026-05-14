import React from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import "../components/common/Common.css"
import { useLocation } from 'react-router-dom';

export default function ChangePassword() {
    const location = useLocation();
    const data = { ...location.state }; ///??{} 새로고침 버그 예방

    return (
        <div className="background-gradient">
            <main className="card-box2">
                <CardTitle title="비밀번호 재설정" />
                <div className="input-wrap">
                    <div className='sub-title'>이메일</div>
                    <div className="input-container">
                        <input className="input2" type='text' value={data.email} disabled/>
                    </div>
                </div>

                <div className="input-wrap">
                    <PasswordBox />
                </div>

                <SubmitButton text="재설정" link='/success' context="비밀번호 재설정 완료" />

            </main>
        </div>
    )
}