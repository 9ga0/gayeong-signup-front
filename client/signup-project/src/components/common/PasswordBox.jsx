import React from 'react';
import { useState, useEffect } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import PasswordInput from './PasswordInput.jsx';
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

    //비밀번호 일치여부 부모들에게 전달하기. 둘중 한 값이라도 바뀌면 match판단 후 부모전달.
    useEffect(() => {        
        if (props.setIsMatch) {
            if (registerParam.pw === registerParam.confirmPw) {
                props.setIsMatch(true);
            } else { props.setIsMatch(false); }
        }
    }, [registerParam.pw,registerParam.confirmPw]);

    return (
        <>
            <div className="sub-title">비밀번호</div>
            <PasswordInput type="input" name='pw'
                placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"
                onSetErrors={setErrors}
                errors={errors}
                registerParam={registerParam}
                setRegisterParam={setRegisterParam}
                setIsMatch={props.setIsMatch}
                img={EyeLock} />
            <PasswordInput type="input" name='confirmPw'
                placeholder="비밀번호 확인"
                onSetErrors={setErrors}
                errors={errors}
                registerParam={registerParam}
                setRegisterParam={setRegisterParam}
                setIsMatch={props.setIsMatch}
                img={EyeLock} />
            {errors.pw && <div className='error'>{errors.pw}</div>}
            {errors.confirmPw && <div className='error'>{errors.confirmPw}</div>}
        </>
    )
}
