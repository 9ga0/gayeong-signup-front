import API from "./API";

//이메일로 특정 유저 정보 조회하는 get api. 로그인성공하면 내 정보 페이지로 넘어갈때 호출
export const getMyInfo = async (email) => {
  try {
    const response = await API.get(
      '/api/v1/users/me', { params: { 'email': email } }
    );
    console.log('api 연결 성공');
    console.log(response.data);
    return response.data;
  }
  catch (error) {
    console.error('getMyInfo에서 api 연결 실패:', error.message);
  }
};
