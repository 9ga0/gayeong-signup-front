import React from 'react';
import './styles/App.css'
import { BrowserRouter, Routes , Route } from "react-router-dom";
import LogIn from './pages/LogInPage';
import SignUp from './pages/SignUpPage';

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path ="/" element = {<LogIn />}></Route>
            <Route path ="/SignUp" element = {<SignUp />}></Route>
            <Route path="*" element={<NotFound />} />
        </Routes>
    </BrowserRouter>
  );
};

export default App;