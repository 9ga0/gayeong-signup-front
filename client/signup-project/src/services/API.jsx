import axios from 'axios';

axios.defaults.withCredentials = true;

const API = axios.create({ //axios 인스턴스
  baseURL: import.meta.env.VITE_API_URL,
  withCredentials: true
});

export default API