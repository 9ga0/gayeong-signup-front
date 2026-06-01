import API from "./API";
import axios from 'axios';
import Unauthorized from "../pages/UnauthorizedPage";

export const checkLogin = async () => {
    //불리언을 페이지로 반환하여, 페이지에서 Navigate하기 
    try {
        const response = await API.get(
            '/api/v1/sessions/current', {
        })
        console.log('로그인 상태를 확인합니다');
        return true;
    } catch (error) {
        if (error.response && error.response.status === 401) {
            console.log("로그인되어있지 않음");
        }
        else console.error('checkLogin에서 api 연결 실패:', error.message);
        console.log(error.response.data);

        return false;
    }
}