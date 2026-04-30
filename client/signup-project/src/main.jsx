import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
//import App from './App.jsx'
import LogInPage from "./pages/LogInPage.jsx";
import SignUpPage from "./pages/SignUpPage";

createRoot(document.getElementById('root')).render(
  <StrictMode>
        {/*라우터 연결 실패로 인해 직접 페이지 바꾸면서 페이지 화면 확인*/}
    <SignUpPage />  
  </StrictMode>,
)