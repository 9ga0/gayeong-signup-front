import React from 'react';
import './Common.css';

const onChangeEmailHandler = (e) => { //이메일 입력받는 핸들러
    const idValue = e.target.value;
    setId(idValue);
    emailCheckHandler(idValue); 
  }
const emailCheckHandler = async (email) => {
    const EMAIL_REGEX = /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    if (email === '') {
        //빈칸일 때: non-pass상태
      setIdError('이메일을 입력해주세요.');
      setIsIdAvailable(false);
      return false;
    } else if (!EMAIL_REGEX.test(id)) {
        //메일 형식 지키지 않았을 때: non-pass
      setIdError('유효하지 않은 메일입니다.');
      setIsIdAvailable(false);
      return false;
    }
    try {
      const responseData = await idDuplicateCheck(id); //중복데이터 아닐때 true로 작동
      if (responseData) {
        //사용가능한 이메일일때 세팅
        //: pass: 체크 아이콘 추가, 파란테두리 적용
        setIsIdCheck(true);
        setIsIdAvailable(true);
        return true;
      } else {
        //중복된 메일 일때: non-pass: 엑스 아이콘 추가, 빨간 테두리. 
        setIdError('중복된 메일입니다.');
        setIsIdAvailable(false);
        return false;
      }
    } catch (error) {
      alert('서버 오류입니다. 관리자에게 문의하세요.');
      console.error(error);
      return false;
    }
}

function EmailBox(){
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
            <input onChange={onChangeEmailHandler} id="input" placeholder="이메일"/>
          </div>
            <input id="input" placeholder="인증번호" />
        </>
    )
};

export default EmailBox;