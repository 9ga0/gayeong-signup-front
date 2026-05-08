import React from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import { useLocation } from "react-router-dom";
import "../components/common/Common.css"

export default function ChangePassWord(props){
    return(
        <div id="background_gradient">
              <main id="card_box">
                    <CardTitle title="비밀번호 재설정" />
                    <div id="input_wrap">
                        <p>이메일<br/> </p>
                        <div class="input_container">
                            <input id="input2" type="email" text={props.email}/>
                        </div>
                    </div>
                    
                    <div id="input_wrap">
                        <PasswordBox/>
                    </div>
                    
                    <SubmitButton text="재설정" link='/success' context="비밀번호 재설정 완료" />
            </main>
        </div>
    )
}