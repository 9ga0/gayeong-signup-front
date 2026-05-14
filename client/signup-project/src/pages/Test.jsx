import axios from 'axios';
import React,{ useState } from 'react';
import API from '../services/API';

export default function UserPostComponent() {
  const [data, setData] = useState(null);

  const handlePost = async (emailAddress) => {
    try {
      const response = await axios.get('/api/v1/email-verification/request',
      {
        email: emailAddress,
        headers: {
          'Content-Type': 'application/json',
        }
      });
      console.log(response.data);
      setData(response.data);
    } catch (error) {
      console.error('메일 전송 실패:', error);
    }
  };

  return (
    <div>
      <button onClick={handlePost}>사용자 추가</button>
      {data && <p>결과: {JSON.stringify(data)}</p>}
    </div>
  );
}