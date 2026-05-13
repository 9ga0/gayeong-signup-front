import React from 'react';
import { useState } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import PasswordInput from './PasswordInput';
import Common from './Common.css';

export default function PasswordBox() {
    let error = '';
    const [errors, setErrors] = useState({
        pw: '',
        confirmPw: ''
    })

    return (
        <>
            <div className="sub-title">비밀번호</div>
            <PasswordInput type="input" name='pw'
                placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"
                onSetErrors={setErrors} 
                errors={errors}
                img={EyeLock}/>
            <PasswordInput type="input" name='confirmPw'
                placeholder="비밀번호 확인"
                onSetErrors={setErrors} 
                errors={errors} 
                img={EyeLock}/>
            {errors.pw && <div className='error'>{errors.pw}</div>}
            {errors.confirmPw && <div className='error'>{errors.confirmPw}</div>}
        </>
    )
}
