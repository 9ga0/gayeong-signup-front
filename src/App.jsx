import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LogIn from './pages/LogIn.jsx';
import SignUp from './pages/SignUp.jsx';
import FindPassword from './pages/FindPassword.jsx';
import ChangePassword from './pages/ChangePassword.jsx';
import Success from './pages/Success.jsx';
import RegisterComponent from './pages/TestSignUp.jsx'
import UserPostComponent from './pages/Test.jsx';
import MyPage from './pages/MyPage.jsx';
import AdminPage from './pages/AdminPage.jsx';
import NotFound from './pages/NotFound.jsx';
import Unauthorized from './pages/UnauthorizedPage.jsx';
import ChatBot from './pages/ChatBotPage.jsx';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={< LogIn />} />
          <Route path ="/login" element = {< LogIn/>}/>
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/find-password" element={<FindPassword />} />
          <Route path="/change-password" element={<ChangePassword />} />
          <Route path="/success" element={<Success />} />
          <Route path="/test" element={<UserPostComponent />} />
          <Route path="/my-page" element={<MyPage />} />
          <Route path="/admin-page" element={<AdminPage />} />
          <Route path="/unauthorized" element={<Unauthorized />} />
          <Route path="/chatbot" element={<ChatBot />} />

          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;