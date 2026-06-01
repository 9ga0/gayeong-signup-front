import API from "./API";


//이메일로 특정 유저 정보 조회하는 get api. 로그인성공하면 내 정보 페이지로 넘어갈때 호출
export const logout = async (e) => {
    try {
        const response = await API.delete(
            '/api/v1/sessions/current'
        );
        console.log('logout api 연결 성공');
    }
    catch (error) {
        console.error('logout api 연결 실패:', error.message);
    }
};
