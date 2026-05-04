import React from 'react';
import './Common.css';

function EmailBox(){
    return(
        <>
          <p>이메일<br/> </p>
          <div>
            <button
                type="button"
                className="email_button"
                >
                전송
            </button>
            <input id="input" placeholder="이메일"/>
          </div>
            <input id="input" placeholder="인증번호" />
        </>
    )
};

export default EmailBox;