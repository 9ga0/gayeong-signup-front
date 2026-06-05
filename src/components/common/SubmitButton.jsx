import React from 'react';
import './Common.css';
import { useNavigate } from "react-router-dom";
import { logout } from '../../services/LogoutApi.jsx';

const SubmitButton = (props) => {
  const navigate = useNavigate();
  const moveToNextPage = async (e) => {
    if (props.link) { //link값이 입력되었으면 이동.
      navigate(props.link, {
        state: {
          context: props.context,
          email: props.email,
          userInfo: props.userInfo
        }
      })
    } else {
      console.log('이동 불가');
    }

    if (props.text === "로그아웃") {
      await logout();
    }
  }
  return (
    <button
      type={props.link ? "button" : "submit"}
      className="submit-button"
      className={props.className} 
      //className 2번하면 뒤의 것이 적용됨. ==className 입력받았으면 위 코드 무시
      style={props.style}
      onClick={moveToNextPage}
    >
      <p className='button-text'>{props.text}</p>
    </button>
  )
}

export default SubmitButton