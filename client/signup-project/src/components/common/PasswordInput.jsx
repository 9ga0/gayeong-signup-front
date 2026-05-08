import React from 'react';
import { useState } from "react";
import EyeLock from "../../assets/EyeLock.svg"
import EyeUnLock from "../../assets/EyeUnLock.svg"

export default function PasswordInput(props) {
    const [visible, setVisible] = useState(false);
    const [imageSrc, setImageSrc] = useState(EyeLock);

    const initState = {
        pw: '',
        confirmPw: ''
    }

    const handleClick = () => {
        if (visible) {
            setImageSrc(EyeLock);
            setVisible(false);
        } else {
            setImageSrc(EyeUnLock);
            setVisible(true); 
        }
    };

    const handleChange = (e) => {
        props.setIsAccepted(true);
    }

    return (
        <div className="input_container">
            <input className="input2"
                name={props.name}
                type={visible ? "text" : "password"}
                placeholder={props.placeholder}
                onChange={handleChange}
            //비밀번호 입력하면 isAccepted로 입력중임을 (PasswordBox/SignUp)에 전달
            /><br />
            <div >
                <img className="visible_btn"
                    className="input_img"
                    onChange={handleClick} 
                    //눈아이콘 누르면 아이콘 변경+visible변경
                    src={imageSrc}
                />
            </div>
        </div>
    )
}
