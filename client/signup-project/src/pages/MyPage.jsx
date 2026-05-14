import React from 'react';
import CardTitle from "../components/common/CardTitle"
import PasswordBox from "../components/common/PasswordBox"
import SubmitButton from '../components/common/SubmitButton';
import '../components/common/Common.css'
export default function MyPage(props) {
    return (
        <div className="background-gradient">
            <main className="card-box2">
                <CardTitle title="내정보" />
                
                <p>로그인 성공!</p> 
                {/* props로 값 전달받아 내정보 출력? 으로 변경 */}

                <SubmitButton text="로그아웃" link='/' />

            </main>
        </div>
    )
}