import React from 'react';
import './Common.css';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Check from "../../assets/Check.svg"
import Fail from "../../assets/Fail.svg"
import axios from 'axios';
//import sendMail from '../../utils/API.jsx';
import API from '../../services/API.jsx';
//import EmailPostApi from '../../services/EmailApi.jsx';
//(api함수 따로 services에 파서 어떻게 하는지 모르겟다..)

export default function EmailBox() {
  const initState = {
    email: '',
    authentication: ''
  }
  const [borderColor, setBorderColor] = useState('#89848466');
  const [registerParam, setRegisterParam] = useState({ ...initState })
  const [imageSrc, setImageSrc] = useState(null);
  const [isActive, setIsActive] = useState(false);
  const [resend, setResend] = useState(false); //재전송
  const [data, setData] = useState(null);
  const [sendText, setSendText] = useState(null);

  const [errors, setErrors] = useState({
    email: '',
    verification: ''
  })

  //유효판단 및 에러메시지 등 상태 변경
  const validateField = (name, value, pwValue) => {
    let error = ''
    ///(utill폴더 파서 중앙집중으로 유효검증모으기)
    const emailRegex = /\S+@\S+\.\S+/;
    switch (name) {
      case 'email':
        if (!value) {
          error = '이메일을 입력해주세요.'
          setBorderColor('#EE4346A6');
          setImageSrc(Fail);
          setIsActive(false);
        } else if (!emailRegex.test(value)) {
          error = '유효하지 않은 메일입니다.'
          setBorderColor('#EE4346A6');
          setImageSrc(Fail);
          setIsActive(false);
        }
        else if (emailRegex.test(value)) { //통과
          setImageSrc(Check);
          setIsActive(true);
          setBorderColor('#435DEEA6');
        }
        else {
          setBorderColor('#89848466');
          setIsActive(false);
        }
        break
      case 'verification':
        if (!value) {
          error = ''
        }
      default:
        break
    }
    return error
  }

  //입력된 값 저장하고, 에러메시지 저장. 인증번호전송문구초기화
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
    if (name === 'authentication') handleEqual(e);

    setSendText(null);
  }

  //메일로 인증번호 보내는 함수
  const handlePost = async (e) => {
    e.preventDefault();
    try {
      const response = await API.post(
        '/api/v1/email-verification/request',
        { email: registerParam.email });

      console.log(response.data);
      setData(response.data);
      //보내기 성공
      resend ? setSendText('인증번호가 재전송되었습니다.') : setSendText('인증번호가 전송되었습니다.');
      setResend(true);
    } catch (error) {
      console.error('api 연결 실패:', error);
    }
  }

  //인증번호 일치 판단API 함수
  const handleEqual = async (e) => {
    e.preventDefault();
    try {
      const response = await API.post(
        '/api/v1/email-verification/confirm',
        { verificationCode: registerParam.verification }
      );
      console.log('인증번호 일치: ', response.data);

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
        {isActive ? //이메일 형식에 맞을 시 true
          <button type="submit" className="email-button">
            <p className='email-button-text'> {resend ? '재전송' : '전송'} </p>
          </button>
          :
          <button className="email-button" style={{ backgroundColor: "#E3E3E3" }}>
            <p className='email-button-text'> 전송 </p>
          </button>
        }
      </form>
      <div className="input-container">
        <input
          className="input2"
          name="authentication"
          value={registerParam.verification}
          placeholder="인증번호"
          onChange={handleChange} />
      </div>
      {errors.email && <div className='error'>{errors.email}</div>}
      {sendText && <div className='error' style={{ color: '#435DEEA6' }}>{sendText}</div>}

    </div >
  )
};