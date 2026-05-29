import React from 'react';
import axios from 'axios';
import API from "./API";

//메일로 인증번호 보내는 함수
export const EmailPostApi = async (e) => {
    e.preventDefault();
    try {
        const response = await API.post(
            '/api/v1/email-verifications');

        console.log(response.data);
    } catch (error) {
        console.error('api 연결 실패:', error);
    }
}