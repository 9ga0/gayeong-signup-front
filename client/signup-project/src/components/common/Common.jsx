
// p(title), 
//EmailBox: [Input][sendButton] [input]
//제출하기버튼(text), input(text), 

const CardTitle = (props) => {
  return (
    <div>
      <h1>{props.title}</h1>
    </div>
  ) 
};
// 내부함수인데 작은 애들 function vs const

const EmailBox =() =>{
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
            <input id="input" type="email" placeholder="이메일"/>
          </div>
            <input id="input" type="text" placeholder="인증번호" />
        </>
    )
}

const InputBox=(props)=>{
    return(
      <div class="input_box">
        {/* css로만  클래스, 컴포너늩 */}
        {props.children}
      </div>
    )
}

const SubmitButton=(title)=>{
  return(
    <button
      type="submit"
              className="submit_button"
            >
            <p>{title}</p>
        
    </button>
  )
} 

export default CardTitle
