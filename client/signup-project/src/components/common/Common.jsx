
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

const InputBox=(children)=>{
    return(
      <div class="input_box">
        {children}
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
