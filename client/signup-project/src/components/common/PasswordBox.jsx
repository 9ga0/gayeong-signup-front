import React from 'react';
import { useState,useEffect } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import PasswordInput from './PasswordInput';
import './Common.css';

export default function PasswordBox(props) {
    let error = '';
    const [errors, setErrors] = useState({
        pw: '',
        confirmPw: ''
    })
    const initState = {
        pw: '',
        confirmPw: ''
    }
    const [registerParam, setRegisterParam] = useState({ ...initState })
    const [correctTurn, setCorrectTurn] = useState(false);

    //password값이 바뀌면 부모에게 전달 실행
    useEffect(() => {
        if (props.onSetPassword) {
            props.onSetPassword({
                target: {
                    name: 'password', 
                    value: registerParam.pw
                }
            });
        }
    }, [registerParam.pw]); 
    return (
        <>
            <div className="sub-title">비밀번호</div>
            <PasswordInput type="input" name='pw'
                placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"
                onSetErrors={setErrors}
                errors={errors}
                registerParam={registerParam}
                setRegisterParam={setRegisterParam}
                setCorrectTurn={setCorrectTurn}
                img={EyeLock} />
            <PasswordInput type="input" name='confirmPw'
                placeholder="비밀번호 확인"
                onSetErrors={setErrors}
                errors={errors}
                registerParam={registerParam}
                setRegisterParam={setRegisterParam}
                correctTurn={correctTurn}
                img={EyeLock} />
            {errors.pw && <div className='error'>{errors.pw}</div>}
            {errors.confirmPw && <div className='error'>{errors.confirmPw}</div>}
        </>
    )
}
