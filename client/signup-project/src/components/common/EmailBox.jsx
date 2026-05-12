import React from 'react';
import './Common.css';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Check from "../../assets/Check.svg"
import Fail from "../../assets/Fail.svg"
import axios from 'axios';
//import sendMail from '../../utils/API.jsx';
import API from '../../services/API.jsx';

export default function EmailBox() {
  const initState = {
    email: '',
    authentication:''
  }
  const [borderColor, setBorderColor] = useState('#89848466');
  const [registerParam, setRegisterParam] = useState({ ...initState })
  const [imageSrc, setImageSrc] = useState(null);

  const [errors, setErrors] = useState({
    email: '',
    verification:''
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
      case 'verification':
        if(!value){
          error = ''
        }
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
    if (name==='authentication') handleEqual(e);
  }
  const [data, setData] = useState(null);

  const handlePost = async (e) => {
    e.preventDefault();
    try {
      const response = await API.post(
        '/api/v1/email-verification/request',
        {email: registerParam.email});

      console.log(response.data);
      setData(response.data);
    } catch (error) {
      console.error('api 연결 실패:', error);
    }
  }//service폴더

  const handleEqual = async (e)=>{
    e.preventDefault();
    try {
      const response = await API.post(
        '/api/v1/email-verification/confirm',
         {verificationCode: registerParam.verification}
      );
      console.log('인증번호 일치: ',response.data);
      
    } catch (error) {
      console.error('api 연결 실패:', error);
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
        <input 
        className="input2" 
        name="authentication"
        value={registerParam.verification}
        placeholder="인증번호" 
        onChange={handleChange}/>
      </div>
      {errors.email && <div className='error'>{errors.email}</div>}
    </div>
  )
};