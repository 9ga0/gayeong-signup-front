import React from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import { useLocation } from "react-router-dom";
import "../components/common/Common.css"

export default function ChangePassWord(props){
    return(
        <div className="background_gradient">
              <main className="card_box">
                    <CardTitle title="비밀번호 재설정" />
                    <div className="input_wrap">
                        <div className='sub-title'>이메일</div>
                        <div class="input_container">
                            <input className="input2" type="email" value={props.email}/>
                        </div>
                    </div>
                    
                    <div className="input_wrap">
                        <PasswordBox/>
                    </div>
                    
                    <SubmitButton text="재설정" link='/success' context="비밀번호 재설정 완료" />
            </main>
        </div>
    )
}