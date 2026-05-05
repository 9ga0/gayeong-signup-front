import React from 'react';

import EyeLock from "../../assets/EyeLock.svg"
import EyeUnLock from "../../assets/EyeUnLock.svg"
/*
const onClickPasswordLockhandle = () => {
  if (isClicked) {
    setImageSrc({EyeLock});
      setIsClicked(false); // 초기 상태 false 일 땐 초기 상태 이미지 src
    } else {
      setImageSrc({EyeUnLock});
      setIsClicked(true); // true일 땐 변경될 이미지 src
    }
};*/
export default function PasswordBox(props){
    return(
        <>
            <p>비밀번호<br/> </p>
            <div class="input_container">
            <input id="input2" 
                value={props.password}
                type={props.visible ? "text": "password"}
                onChange={(e) => setPassword(e.target.value)}
                type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <img className="visible_btn" class="input_img" src={EyeLock} /*onClick={onClickPasswordLockhandle}*//>              
            </div>
            <div class="input_container">
                <input id="input2" type="password" placeholder="비밀번호 확인"/>
                <img className="visible_btn" class="input_img" src={EyeLock} /*onClick={onClickPasswordLockhandle}*//>
                              
            </div>
        </>
    )
}