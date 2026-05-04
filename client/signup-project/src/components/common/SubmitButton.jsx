import React from 'react';
import './Common.css';
import {useNavigate} from "react-router-dom";

const SubmitButton=(props)=>{
  const navigate =useNavigate();

  return(
    <button
          type="submit"
          className="submit_button"
          onClick={()=> {navigate(props.link)}}
        >
            <p>{props.text}</p>
    </button>
  )
} 

export default SubmitButton