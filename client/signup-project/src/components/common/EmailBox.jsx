import React from 'react';
import './Common.css';
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function EmailBox() {
  const initState = {
    email: ''
  }
  const [registerParam, setRegisterParam] = useState({ ...initState })
  const [isOpen, setIsOpen] = useState(false) // 주소찾기 모달 열기/닫기 상태
  const [errors, setErrors] = useState({
    email: ''
  })
  const validateField = (name, value, pwValue) => {
    let error = ''
    switch (name) {
      case 'email':
        if (!value) {
          error = '이메일을 입력해주세요.'
        } else if (!/\S+@\S+\.\S+/.test(value)) {
          error = '유효하지 않은 메일입니다.'
        }
        break
      default:
        break
    }
    return error
  }
  const handleChange = (e) => {
    const { name, value } = e.target

    setRegisterParam({
      ...registerParam,
      [name]: value
    });

    let error
    error = validateField(name, value)
    setErrors({
      ...errors,
      [name]: error
    })
  }
  
  return (
    <>
      <div className="sub-title">이메일</div>
      <div className="line_box">
        <div className="input_container">
          <input 
            className="input2" 
            name="email"
            type={'text'}
            placeholder="이메일" 
            value={registerParam.email}
            onChange={handleChange}
            />
        </div>
        <button
          type="button"
          className="email_button"
        >
          <p className='email_button_text'> 전송 </p>
        </button>
      </div>
      <div className="input_container">
        <input className="input2" placeholder="인증번호" />
      </div>
      {errors.email && <div className='error'>{errors.email}</div>}
    </>
  )
};