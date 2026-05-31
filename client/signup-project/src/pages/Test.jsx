import axios from 'axios';
import React, { useEffect, useState } from 'react';
import API from '../services/API';

export default function UserPostComponent() {
  const [saveIDFlag, setSaveIDFlag] = useState(false);
  const LS_KEY_ID = "LS_KEY_ID";
  const LS_KEY_SAVE_ID_FLAG = "LS_KEY_SAVE_ID_FLAG";
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  })

  // 아이디 저장 체크박스 활성화
  const handleRememberId = () => {
    console.log("check 발생");
    localStorage.setItem(LS_KEY_SAVE_ID_FLAG, !saveIDFlag);
    setSaveIDFlag(!saveIDFlag);
  };

  // 컴포넌트가 처음 마운트 될 때만 실행되도록 빈 배열을 두번째 인자로 넘김
  useEffect(() => {
    // 로컬스토리지에서 저장 활성화 버튼 여부
    let idFlag = JSON.parse(localStorage.getItem(LS_KEY_SAVE_ID_FLAG));

    if (idFlag !== null) setSaveIDFlag(idFlag);

    // 저장이 안되어 false일 경우 ""로 저장
    if (idFlag === false) localStorage.setItem(LS_KEY_ID, "");

    // storage 에서 꺼낸 값 id 값
    let data = localStorage.getItem(LS_KEY_ID);

    // 저장되어있는 값이 있을 경우
    // 데이터가 null이 아닐 경우
    if (data !== null) setFormData({ email: data });
  }, []);

  const handleSubmit = async (e) => {
    console.log("handleSubmit() 호출");
    e.preventDefault();
    //console.log("email : ", formData.email);
    if (formData.email === "") {
      alert("이메일을 입력해주세요.");
      return false;
    }
    if (formData.password === "") {
      alert("비밀번호를 입력해주세요");
      return false;
    }

    if (saveIDFlag) {
      localStorage.setItem(LS_KEY_ID, formData.email);
    }

    try {
      const response = await API.post(`/api/login`, formData);
      console.log("formData : ", formData);
    } catch (error) {
      console.log("error.status :", error.response.status);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  return (
    <form className="user" id="loginForm">
      <div className="form-group">
        <input
          type="email"
          name="email"
          onChange={handleChange}
          className="form-control form-control-user"
          aria-describedby="emailHelp"
          placeholder="Enter Email Address..."
          value={formData.email}
        />
      </div>
      <div className="form-group">
        <input
          type="password"
          name="password"
           onChange={handleChange}
          autoComplete="off"
          className="form-control form-control-user"
          placeholder="Password"
        />
      </div>
      <div className="form-group">
        <div className="custom-control custom-checkbox small">
          <input
            onChange={handleRememberId} //
            type="checkbox"
            checked={saveIDFlag}
            className="custom-control-input"
            id="rememberId"
          />
          <label
            className="custom-control-label"
            htmlFor="rememberId"
          >
            Remember Me
          </label>
        </div>
      </div>
      <button
        onClick={handleSubmit}
        className="btn btn-primary btn-user btn-block"
      >
        로그인
      </button>
      <hr />
    </form>
  );
}