import React from 'react';
import './styles/App.css'
import { BrowserRouter, Routes , Route } from "react-router-dom";
import LogIn from './pages/LogIn';
import SignUp from './pages/SignUp';

function App() {
  return (
    <div>
        <LogIn />
        <Routes>
            <Route path ="/" element = {<LogIn />}/> 
            {/* 루트 */}
            <Route path ="/SignUp" element = {<SignUp />}/>
            {/* 소문자  */}
            <Route path="*" element={<NotFound />} />
        </Routes>
    </div>
  );
};

export default App;