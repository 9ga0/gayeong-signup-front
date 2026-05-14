import React from 'react';
import { useState } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import EyeUnLock from "../../assets/EyeUnLock.svg"

export default function PasswordInput(props) {
    const [visible, setVisible] = useState(false);
    const [imageSrc, setImageSrc] = useState(props.img);
    const [correctTurn, setCorrectTurn] = useState(false);

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
                    if (props.setCorrectTurn) { props.setCorrectTurn(false); }
                }
                else if (!passwordRegex.test(value)) {
                    error = '8~16자의 영문 대/소문자, 숫자, 특수문자'
                    setBorderColor('#EE4346A6');
                    if (props.setCorrectTurn) { props.setCorrectTurn(false); }
                }
                else if (passwordRegex.test(value)) {//통과. 유효한 비밀번호입력
                    setBorderColor('#435DEEA6');
                    if (props.setCorrectTurn) { props.setCorrectTurn(true); }
                }
                else {
                    setBorderColor('#89848466');
                }
                break
            case 'confirmPw':
                if (props.correctTurn && value && value !== pwValue) {
                    error = '비밀번호가 일치하지 않습니다.'
                    setBorderColor('#EE4346A6');
                }
                else if (props.correctTurn && value) { //비밀번호 일치
                    setBorderColor('#435DEEA6');
                }
                else {
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
        if (props.setPassword) props.setPassword(e.target.value);

        if (props.setRegisterParam) {
            props.setRegisterParam((prev) => ({
                ...prev,
                [name]: value
            }));
        }

        let error
        if (e.target.name === 'confirmPw') { // confirmPw 필드에 입력할 때마다 현재 pw 필드의 값과 비교하여 에러 체크
            if (props.correctTurn) {
                error = validateField(name, value, props.registerParam.pw)
            }
        } else { //confirm 필드가 비어있을 때는 에러 메시지를 표시하지 않음
            if (props.registerParam) { //유효판단 안해도되는 로그인인풋에서 거르기 위함.
                error = validateField(name, value)
            }
        }
        if (props.onSetErrors) {
            props.onSetErrors({ //부모에게 전달 가능
                ...props.errors,
                [name]: error
            })
        }
        if (props.registerParam) {       // pw가 변경되었을 때도 confirmPw 필드의 값을 재검증하여 에러 상태 업데이트
            if (e.target.name === 'pw' && props.registerParam.confirmPw) {
                const confirmPwError = validateField('confirmPw', props.registerParam.confirmPw, value)
                props.onSetErrors(prev => ({
                    ...prev,
                    confirmPw: confirmPwError
                }))
            }
        }

        if (imageSrc !== EyeUnLock) setImageSrc(EyeLock);
        props.isLogin ? setBorderColor('#89848466') : null;
    }


    return (
        <div className="input-container"
            style={{ border: `2px solid ${borderColor}` }}>
            <input className="input2"
                name={props.name}
                type={visible ? "text" : "password"}
                placeholder={props.placeholder}
                onChange={handleChange}
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
