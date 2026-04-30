//import reactLogo from './assets/react.svg' //이미지 불러오기
import '../styles/LogInPage.css' //css 불러오기

function LoginPage() {
  return (
    <>
      <div id="background_gradient">  
        <main id="signup_box"> 
          <h1>En# SignUp!!</h1> 

          <form class="input_box">
            <input id="input" type="email" placeholder="E-mail"/>
            <input id="input" type="password" placeholder="Password"/>
            <div id="line_box">
              <input id="checkbox" type="checkbox"/>아이디 저장
              <p>비밀번호 찾기</p>
            </div>

          </form>

            
          <button 
            type="button"
            className="submit_button"
            >Login</button>

          <p>계정이 없으신가요?</p> 회원가입
        </main>
      </div>

    </>
  )
}

export default LoginPage
