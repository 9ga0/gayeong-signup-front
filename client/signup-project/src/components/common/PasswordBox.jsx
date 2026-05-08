import React from 'react';
import { useState } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import EyeUnLock from "../../assets/EyeUnLock.svg"
import PasswordInput from './PasswordInput';

export default function PasswordBox() {
    const initState = {
        pw: '',
        confirmPw: ''
    }
    const [input, setInput] = useState('');

    const [registerParam, setRegisterParam] = useState({ ...initState })
    const [isAccepted, setIsAccepted] = useState(false)
    let error = '';
    const [errors, setErrors] = useState({
        pw: '',
        confirmPw: ''
    })
    const validateField = (name, value, pwValue) => {
        let error = ''
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
        // 하나 이상의 대문자, 하나 이상의 소문자, 하나이상의 숫자, 하나이상의 특수문자 가 8자~16자로 구성
        switch (name) {
            case 'pw':
                if (!value) {
                    error = '비밀번호를 입력해주세요.'
                }
                else if (!passwordRegex.test(value)) {
                    error = '8~16자의 영문 대/소문자, 숫자, 특수문자'
                }
                break
            case 'confirmPw':
                if (value && value !== pwValue) {
                    error = '비밀번호가 일치하지 않습니다.'
                }
                break
            default:
                break
        }
        return error
    };

    const handleChange = (e) => {
        const { name, value } = e.target
        setInput(e.target.value);

        setRegisterParam({
            ...registerParam,
            [name]: value
        });

        let error
        if (name === 'confirmPw') { // confirmPw 필드에 입력할 때마다 현재 pw 필드의 값과 비교하여 에러 체크
            error = validateField(name, value, registerParam.pw)
        } else { // confirm 필드가 비어있을 때는 에러 메시지를 표시하지 않음
            error = validateField(name, value)
        }
        setErrors({
            ...errors,
            [name]: error
        })

        // pw가 변경되었을 때도 confirmPw 필드의 값을 재검증하여 에러 상태 업데이트
        if (name === 'pw' && registerParam.confirmPw) {
            const confirmPwError = validateField('confirmPw', registerParam.confirmPw, value)
            setErrors(prev => ({
                ...prev,
                confirmPw: confirmPwError
            }))
        }
    }

    return (
        <>
            <div className="sub-title">비밀번호</div>
            <PasswordInput type="input" name='pw' value={registerParam.pw} setIsAccepted={handleChange} placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자" />
            <PasswordInput  type="input" name='confirmPw' value={registerParam.confirmPw} setIsAccepted={handleChange} placeholder="비밀번호 확인" />
            {errors.pw && <div className='error'>{errors.pw}</div>}
            {errors.confirmPw && <div className='error'>{errors.confirmPw}</div>}
        </>
    )
}
