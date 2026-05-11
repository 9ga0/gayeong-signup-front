import React from 'react';
import { useState } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import EyeUnLock from "../../assets/EyeUnLock.svg"

export default function PasswordInput(props) {
    const initState = {
        pw: '',
        confirmPw: ''
    }
    const [visible, setVisible] = useState(false);
    const [imageSrc, setImageSrc] = useState(EyeLock);
    const [registerParam, setRegisterParam] = useState({ ...initState })
    const handleClick = () => {
        if (visible) {
            setImageSrc(EyeLock);
            setVisible(false);
        } else {
            setImageSrc(EyeUnLock);
            setVisible(true);
        }
    };

    const [borderColor, setBorderColor] = useState('#89848466');

    const validateField = (name, value, pwValue) => {
        let error = ''
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
        // 하나 이상의 대문자, 하나 이상의 소문자, 하나이상의 숫자, 하나이상의 특수문자 가 8자~16자로 구성
        switch (name) { 
            case 'pw':
                if (!value) {
                    error = '비밀번호를 입력해주세요.'
                    setBorderColor('#EE4346A6');
                }
                else if (!passwordRegex.test(value)) {
                    error = '8~16자의 영문 대/소문자, 숫자, 특수문자'
                    setBorderColor('#EE4346A6');
                }
                else if(passwordRegex.test(value)){//통과
                    setBorderColor('#435DEEA6');
                }
                else {
                    setBorderColor('#89848466');
                }
                break
            case 'confirmPw':
                if (value && value !== pwValue) {
                    error = '비밀번호가 일치하지 않습니다.'
                    setBorderColor('#EE4346A6');
                }
                else if(value) { //비밀번호 일치
                    setBorderColor('#435DEEA6');
                }
                else{
                    setBorderColor('#89848466');
                }
                break
            default:
                break
        }
        return error
    };

    const handleChange = (e) => {
        const { name, value } = e.target;

        setRegisterParam({
            ...registerParam,
            [name]: value
        });

        let error
        if (e.target.name === 'confirmPw') { // confirmPw 필드에 입력할 때마다 현재 pw 필드의 값과 비교하여 에러 체크
            error = validateField(name, value, registerParam.pw)
        } else { //confirm 필드가 비어있을 때는 에러 메시지를 표시하지 않음
            error = validateField(name, value)
        }
        props.onSetErrors({ //부모에게 전달 가능
            ...props.errors,
            [name]: error
        })

        // pw가 변경되었을 때도 confirmPw 필드의 값을 재검증하여 에러 상태 업데이트
        if (e.target.name === 'pw' && registerParam.confirmPw) {
            const confirmPwError = validateField('confirmPw', registerParam.confirmPw, value)
            props.onSetErrors(prev => ({
                ...prev,
                confirmPw: confirmPwError
            }))
        }
    }


    return (
        <div className="input-container"
            style={{ border: `2px solid ${borderColor}` }} >
            <input className="input2"
                name={props.name}
                type={visible ? "text" : "password"}
                placeholder={props.placeholder}
                onChange={handleChange}
            //비밀번호 입력하면 isAccepted로 입력중임을 (PasswordBox/SignUp)에 전달
            />
            <div >
                <img className="input-img"
                    onClick={handleClick}
                    //눈아이콘 누르면 아이콘 변경+visible변경
                    src={imageSrc}
                />
            </div>
        </div>
    )
}
