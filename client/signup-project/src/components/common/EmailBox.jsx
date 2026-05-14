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

export default function EmailBox(props) {
  const initState = {
    email: '',
    verification: ''
  }
  const [borderColor, setBorderColor] = useState('#89848466');
  const [registerParam, setRegisterParam] = useState({ ...initState })
  const [imageSrc, setImageSrc] = useState(null);
  const [isActive, setIsActive] = useState(false);
  const [sendText, setSendText] = useState(null);
  const [isSend, setIsSend] = useState(false);
  const [isCorrect, setIsCorrect] = useState(false);

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
        else if (props.isExistEmail) {
          error = '이미 존재하는 이메일입니다.'
          setBorderColor('#EE4346A6');
          setImageSrc(Fail);
          setIsActive(false);
        }
        else if (emailRegex.test(value)) { //통과.유효한이메일입력
          setImageSrc(Check);
          setIsActive(true);
          setBorderColor('#435DEEA6');
        }
        else {
          setBorderColor('#89848466');
          setIsActive(false);
        }
        setIsSend(false);
        break
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
    if (isSend && name === 'verification') {
      console.log(e.target.value);
      handleEqual(e, e.target.value);
    }

    setSendText(null);
    //props.value = registerParam.email; //email값을 부모에게 넘겨줌.
  }

  //메일로 인증번호 보내는 함수
  const handlePost = async (e) => {
    e.preventDefault()
    try {
      const response = await API.post(
        '/api/v1/email-verification/request',
        { email: registerParam.email });

      console.log(response.data); //인증 번호가 발송되었습니다
      console.log(response.status); //200
      props.onSetEmail({ //부모에게 이메일 객체 전달
                      target: {
                          name: 'email', 
                          value: registerParam.email
                      }
                  });
      
      //보내기 성공
      isSend ? setSendText('인증번호가 재전송되었습니다.') : setSendText('인증번호가 전송되었습니다.');
      setIsSend(true);
      handleEqual(e, e.target.value);
    } catch (error) {
      console.error('handlePost에서 api 연결 실패:', error);
    }
  }

  //인증번호 일치 판단API 함수
  const handleEqual = async (e, number) => {
    e.preventDefault()
    try {
      const response = await API.post(
        '/api/v1/email-verification/confirm', {
        email: registerParam.email,
        verificationCode: number,
      });
      //response.status===200일때
      console.log(response.status);
      setIsCorrect(true);
      setErrors({ ...errors, ['email']: '' });
      setSendText('올바른 인증번호입니다.');

    } catch (error) {
      if (error.response && error.response.status === 400) {
        console.log(error.response.data); //인증 번호가 일치하지 않습니다
        setIsCorrect(false);
        setErrors({ ...errors, ['email']: '올바르지 않은 인증번호입니다.' });
      }
      console.error('handleEqual에서 api 연결 실패:', error.message);
    }
  }

  return (
    <div className="input-wrap">
      <div className="sub-title">이메일</div>
      <div className="line-box" >
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
          <button type="button" className="email-button" onClick={handlePost} >
            <p className='email-button-text'> {isSend ? '재전송' : '전송'} </p>
          </button>
          : //버튼 비활성화
          <button className="email-button" style={{ backgroundColor: "#E3E3E3" }}>
            <p className='email-button-text'> 전송 </p>
          </button>
        }
      </div>
      <div className="input-container">
        <input
          className="input2"
          name="verification"
          type={'text'}
          value={registerParam.verification}
          placeholder="인증번호"
          onChange={handleChange}
        />
      </div>
      {errors.email && <div className='error'>{errors.email}</div>}
      {sendText ? <div className='error' style={{ color: '#435DEEA6' }}>{sendText}</div> : null}

    </div >
  )
};