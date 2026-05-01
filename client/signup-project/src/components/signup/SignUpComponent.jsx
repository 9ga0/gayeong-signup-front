<form class="input_box">
          <CardTitle title={title} />
          <h1>{props.name}</h1> 
          <form class="input_box">

              <p>이메일<br/> </p>
                <input id="input" type="email" placeholder="이메일"/>
                <button
                  type="button"
                  className="email_button"
                >
                 전송
                </button>

              <input id="input" type="text" placeholder="인증번호" />
            <div class="input_box">
              <p>비밀번호<br/> </p>
              <input id="input" type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
              <input id="input" type="password" placeholder="비밀번호 확인"/>
            </div>
            <div class="input_box">
              <p>이름<br/> </p>
              <input id="input" type="text"/>
            </div>
            <div class="input_box">
              <p>주소<br/> </p>
              <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
              <input id="input" type="text" placeholder="상세주소"/>
            </div>
          </form>
        
          <button
            type="submit"
            className="submit_button"
          >
            제출하기
          </button>
    </form>