import axios from 'axios';

const API = axios.create({ //axios 인스턴스
  baseURL: import.meta.env.VITE_API_URL,
});

const sendMail = async () => {
  try {
    const response = await axios.post(
      'https://api.example.com/login', credentials, config);
    console.log(response.data);
  } catch (error) {
    console.error(error);
  } finally {
    console.log("Request completed");
  }
}

export default API