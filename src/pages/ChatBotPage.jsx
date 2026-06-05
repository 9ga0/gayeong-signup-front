//npm install @google/genai
import { useState } from 'react';
import '../components/common/Common.css';
import { GoogleGenerativeAI } from "@google/generative-ai";
import CardTitle from '../components/common/CardTitle';
import '../styles/testChat.css'

const genAI = new GoogleGenerativeAI(import.meta.env.VITE_GEMINI_API_KEY); // 서버 환경변수 사용

export default function ChatBot(props) {
    const [messages, setMessages] = useState([]); //모든대화기록 저장
    const [input, setInput] = useState(""); //입력값
    // const [botMessage, setBotMessage] = useState(""); //챗봇의 대답
    //내 질문과 ai의 응답 저장하는 메시지 배열? 필요할듯

    //모델 객체 생성함
    const model = genAI.getGenerativeModel({ model: "gemini-2.5-flash" });//503 오류 뜨면 2.5,3.5 등 바꿔사용
    const chat = model.startChat({ history: [] });//대화 맥락 유지 가능
    //데이터 베이스에 저장해두고 주입해주면, 새로고침해도 맥락 유지 가능할듯. 일단 새로고침하면 리셋으로.

    //메시지 입력하고 전송버튼 클릭시 동작. 챗봇한데 보내짐
    const handleMessageSend = async () => {
        if (!input.trim()) return; //입력값이 없거나 공백도 거름

        const userMessage = { text: input, sender: 'user' };
        setMessages(prev => [...prev, userMessage]);
        setInput(''); //입력값 null로 다시 설정
        try {
            const result = await chat.sendMessage(input); //챗한테 유저입력 보내기
            const response = await result.response; //챗의 응답 받아 저장
            const botMessage = { text: response.text(), sender: 'bot' };
            setMessages(prev => [...prev, botMessage]);
            console.log(response.text()); //콘솔에 대답 출력
        } catch (error) {
            console.error("chat api 연결 오류발생");
            setMessages(prev => [...prev, { text: "[ERROR] 서버연결에서 오류가 발생하였습니다.", sender: 'bot' }]);

        };
    }
    return (
        <div className="background-gradient">
            <main className="card-box2" >
                <p className='sub-title'>En# ChatBot!!</p>
                <button>내정보</button> {/*이미지 적용하여 버튼으로 사용 예정 */}
                <div className="chat-box">
                    {/* 챗봇과의 대화 출력 */}
                    {messages.map((message,index) => ( //messages 배열을 모두 하나씩 순회하며 message로 꺼냄
                        <div key={index} className={`message ${message.sender}`}>
                            {message.text}
                        </div>
                    ))}
                </div>
                <div className="line-box" >

                    <div className="input-container" >
                        <input
                            className="input2"
                            type="text"
                            placeholder="메시지를 입력하세요..."
                            value={input}
                            onChange={(e) => { setInput(e.target.value) }}
                            onKeyPress={(e) => {
                                if (e.key === 'Enter') { //엔터키 누르면 보내짐.
                                    handleMessageSend();
                                }
                            }}
                        />
                    </div>
                    <button type="button" className="chat-submit-button"
                        onClick={handleMessageSend}>
                        <p className='button-text'>전송</p>

                    </button>
                </div>
            </main>
        </div>
    )
}