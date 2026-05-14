import React, { useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import "../components/common/Common.css"
import { useLocation } from 'react-router-dom';
import API from '../services/API';

export default function ChangePassword(props) {
    const location = useLocation();
    const data = { ...location.state }; ///??{} 새로고침 버그 예방
    const [isMatch, setIsMatch] = useState(false); //비밀번호 입력 및 통과했는지
    const [password, setPassword] = useState('');
    //비밀번호 변경시키는 api 호출.
    const changePw = async (e) => {
        e.preventDefault();
        console.log(data.email, ':', password);
        try {
            const response = await API.patch(
                '/api/v1/auth/password',{
                    email: data.email,
                    password: password
                });
            console.log('비밀번호를 변경했습니다.');
            //console.log(response.status); //200
        } catch (error) {
            console.error('changePw에서 api 연결 실패:', error.message);
        }
    }

    return (
        <div className="background-gradient">
            <form onSubmit={changePw}>
                <main className="card-box2">
                    <CardTitle title="비밀번호 재설정" />
                    <div className="input-wrap">
                        <div className='sub-title'>이메일</div>
                        <div className="input-container">
                            <input className="input2" type='text'
                                value={data.email} disabled />
                        </div>
                    </div>

                    <div className="input-wrap">
                        <PasswordBox name='password'
                            onSetPassword={(e) => { setPassword(e.target.value) }}
                            setIsMatch={setIsMatch} />
                    </div>
                    <SubmitButton text="재설정" onSubmit={changePw}
                            link='/success' isActive={isMatch} context="비밀번호 재설정 완료" />
                </main>
            </form>
        </div>
    )
}
