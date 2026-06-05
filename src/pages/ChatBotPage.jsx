//npm install @google/genai
import { useState } from 'react';
import '../components/common/Common.css';
import { GoogleGenerativeAI } from "@google/generative-ai";
import CardTitle from '../components/common/CardTitle';

const genAI = new GoogleGenerativeAI(import.meta.env.VITE_GEMINI_API_KEY); // 서버 환경변수 사용

export default function ChatBot(props) {
    const [input, setInput] = useState(""); //입력값
    // const [botMessage, setBotMessage] = useState(""); //챗봇의 대답
    //내 질문과 ai의 응답 저장하는 메시지 배열? 필요할듯

    //모델 객체 생성함
    const model = genAI.getGenerativeModel({ model: "gemini-2.5-flash" });//503 오류 뜨면 2.5,3.5 등 바꿔사용
    const chat = model.startChat({ history: [] });

    //메시지 입력하고 전송버튼 클릭시 동작. 챗봇한데 보내짐
    const handleSend = async () => {
        if (!input.trim()) return; //입력값이 없거나 공백도 거름

        //setUserMessage(input);
        setInput(''); //입력값 null로 다시 설정
        try {
            const result = await chat.sendMessage(input); //챗한테 유저입력 보내기
            const response = await result.response; //챗의 응답 받아 저장

            console.log(response.text()); //콘솔에 대답 출력
            //setInput(''); 
        } catch (error) {
            console.error("chat api 연결 오류발생");
            //setBotMessage("출력값이 없습니다");
        };
    }
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <CardTitle title="En# ChatBot!!" />
                <button>내정보</button> { }
                <div >
                    {/* 챗봇과의 대화 출력 */}
                </div>
                <div className="line-box" >

                    <div className="input-container" >
                        <input
                            className="input2"
                            type="text"
                            placeholder="메시지를 입력하세요..."
                            value={input}
                            onChange={(e) => { setInput(e.target.value) }}

                        />
                    </div>
                    <button type="button" className="chat-submit-button"
                        onClick={handleSend}>
                        <p className='button-text'>전송</p>

                    </button>
                </div>
            </main>
        </div>
    )
}