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
            setVisible(false); // 초기 상태 false 일 땐 초기 상태 이미지 src
        } else {
            setImageSrc(EyeUnLock);
            setVisible(true); // true일 땐 변경될 이미지 src
        }
    };

    return (
        <div class="input_container">
            <input id="input2"
                name={props.name}
                type={visible ? "text" : "password"}
                placeholder={props.placeholder} /><br />
            <div >
                <img className="visible_btn" class="input_img"
                    onClick={handleClick} src={imageSrc} />
            </div>
        </div>
    )
}
