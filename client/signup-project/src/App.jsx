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
            <Route path ="/SignUp" element = {<SignUp />}/>
            <Route path="*" element={<NotFound />} />
        </Routes>
    </div>
  );
};

export default App;