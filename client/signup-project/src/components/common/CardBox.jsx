import React from 'react';
import CardTitle from "./Common"
import InputBox from "./Common"
import EmailInput from "./Common"
import SubmitButton from "./Common"

const Scene =(props) =>{
  return (
    <div id="background_gradient">
      <main id="signup_box">
          <div id>
            <CardTitle title={props.title} />
              <InputBox>
                <EmailInput />
              </InputBox>

              <InputBox>
                <p>비밀번호<br/> </p>
                <input id="input" type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <input id="input" type="password" placeholder="비밀번호 확인"/>
              </InputBox>

              <InputBox>
                <p>이름<br/> </p>
                <input id="input" type="text"/>
              </InputBox>

              <InputBox>
                <p>주소<br/> </p>
                <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
                <input id="input" type="text" placeholder="상세주소"/>
              </InputBox>
          </div>
          <SubmitButton title="제출하기" />
      </main>
    </div>
  );
};

export default Scene;
