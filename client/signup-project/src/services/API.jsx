import axios from 'axios';

const API = axios.create({ //axios 인스턴스
  baseURL: import.meta.env.VITE_API_URL,
});
