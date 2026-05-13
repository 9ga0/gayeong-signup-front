import React from 'react';
import './Common.css';
import { useNavigate } from "react-router-dom";

const SubmitButton = (props) => {
  const navigate = useNavigate();
  const moveToNextPage = () => {
      navigate(props.link, {
        state: {
          context: props.context, email: props.email
        }
      })
  }
  return (
    <button
      type="submit"
      className="submit-button"
      style={props.style}
      onClick={moveToNextPage}
    >
      <p className='button-text'>{props.text}</p>
    </button>
  )
}

export default SubmitButton