import React from 'react';
import CardTitle from "./Common"
import InputBox from "./Common"
import EmailBox from "./Common"
import SubmitButton from "./Common"
import './Common.css';

const Scene =(props) =>{
  return (
    <div id="background_gradient">
      <main id="card_box">
          <div>
            <CardTitle title={props.title} />
              <div id="inputBox">
                <EmailBox/>
              </div>

              <div id="inputBox">
                <p>비밀번호<br/> </p>
                <input id="input" type="password" placeholder="8~16자의 영문 대/소문자, 숫자, 특수문자"/><br/>
                <input id="input" type="password" placeholder="비밀번호 확인"/>
              </div>

              <div id="inputBox">
                <p>이름<br/> </p>
                <input id="input" type="text"/>
              </div>

              <div id="inputBox">
                <p>주소<br/> </p>
                <input id="input" type="address" placeholder="클릭하여 주소 검색"/><br/>
                <input id="input" type="text" placeholder="상세주소"/>
              </div>
          </div>
          <SubmitButton title="제출하기" />
      </main>
    </div>
  );
};

export default Scene;
