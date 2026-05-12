import React from 'react';
import './Common.css';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Check from "../../assets/Check.svg"
import Fail from "../../assets/Fail.svg"
import axios from 'axios';
import sendMail from '../../utils/API.jsx';


export default function EmailBox() {
  const initState = {
    email: ''
  }
  const [borderColor, setBorderColor] = useState('#89848466');
  const [registerParam, setRegisterParam] = useState({ ...initState })
  const [imageSrc, setImageSrc] = useState(null);

  const [errors, setErrors] = useState({
    email: ''
  })
  const validateField = (name, value, pwValue) => {
    let error = ''
    //utill폴더 파서 중앙집중으로 유효검증모으기
    const emailRegex = /\S+@\S+\.\S+/;
    switch (name) {
      case 'email':
        if (!value) {
          error = '이메일을 입력해주세요.'
          setBorderColor('#EE4346A6');
          setImageSrc(Fail);
        } else if (!emailRegex.test(value)) {
          error = '유효하지 않은 메일입니다.'
          setBorderColor('#EE4346A6');
          setImageSrc(Fail);
        }
        else if (emailRegex.test(value)) {
          setImageSrc(Check);
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
  const [data, setData] = useState(null);

  const handlePost = async () => {
    try {
      const response = await axios.post('/api/v1/email-verification/request',
        {
          email: registerParam.email,
          headers: {
            'Content-Type': 'application/json',
          }
        });
      console.log(response.data);
      setData(response.data);
      console.log ("메일 전송 성공!");
    } catch (error) {
      console.error('메일 전송 실패:', error);
    }
  }

  return (
    <div className="input-wrap">
      <div className="sub-title">이메일</div>
      <form className="line-box" onSubmit={handlePost}>
        <div className="input-container" style={{ border: `2px solid ${borderColor}` }} >
          <input
            className="input2"
            name="email"
            type={'text'}
            placeholder="이메일"
            value={registerParam.email}
            onChange={handleChange}
          />
          {imageSrc === Check || imageSrc === Fail ? <img className="input-img" src={imageSrc} /> : null}
        </div>
        <button
          type="submit"
          className="email-button"
        >
          <p className='email-button-text'> 전송 </p>
        </button>
      </form>
      <div className="input-container">
        <input className="input2" placeholder="인증번호" />
      </div>
      {errors.email && <div className='error'>{errors.email}</div>}
    </div>
  )
};