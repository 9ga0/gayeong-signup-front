import React, { useEffect, useState } from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
import axios from 'axios';
import API from '../services/API';
import { useLocation } from 'react-router-dom';

//'/admin-page'
export default function AdminPage() {
    const [email, setEmail] = useState("");
    const location = useLocation();
    const [error, setError] = useState("");
    const emailRegex = /\S+@\S+\.\S+/;

    const handleChange = (e) => {
        setEmail(e.target.value)
        console.log(email);
        if (!emailRegex.test(email)) {
            setError("유효하지 않은 이메일 형식입니다")
        }
        else {
            setError("");
        }
    }
    const handleDeleteUser = async (e) => {
        e.preventDefault()

        try {
            const response = await API.delete(
                '/api/v1/admin/users', {
                params: { email: email }
            })

            console.log('해당 회원 정보를 삭제했습니다');
            handlePost(e);
        } catch (error) {
            if (error.response && error.response.status === 401) {
                console.log(error.response.data);
                setError('존재하지 않는 이메일입니다');
            }
            else console.error('handleDeleteUser에서 api 연결 실패:', error.message);
        }
    }
    const handleQuiryAllUser = (e) =>{
        //모든 유저 정보 콘솔?에 출력하기
    }
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="관리자 페이지" />
                <div className='gap-24px'>
                    {/* 현재 링크페이지 notFound라고 뜸 */}
                    <button type="button" className="submit-button"
                        onClick={handleQuiryAllUser}>
                        <p className='button-text'>전체 유저 조회</p>

                    </button>

                    <div className="line-box" >

                        <div className="input-container" >
                            <input
                                className="input2"
                                name="email"
                                type={'text'}
                                placeholder="이메일"
                                value={email}
                                onChange={handleChange}

                            />
                        </div>
                        <button type="button" className="email-button"
                            onClick={handleDeleteUser}>
                            <p className='button-text'>특정 유저 삭제</p>

                        </button>
                    </div>
                    {error && <div className='error'>{error}</div>}

                    <SubmitButton text="이메일 인증 기록 전체 삭제" link='/delete-email-verification-history' isActive={true} />
                </div>
                <SubmitButton text="로그아웃" link='/login' isActive={true} />

            </main>
        </div>
    )
}
