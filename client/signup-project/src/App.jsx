import React from 'react';
import './styles/App.css'
import { BrowserRouter, Routes , Route } from "react-router-dom";
import LogIn from './pages/LogIn';
import SignUp from './pages/SignUp';
import FindPassWord from './pages/FindPassWord';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
            <Route path ="/" element = {< LogIn/>}/> 
            {/* <Route path ="/log-in" element = {< LogIn/>}/> */}
            <Route path ="/sign-up" element = {<SignUp />}/>
            <Route path ="/find-password" element = {<FindPassWord />}/>

            {/* <Route path="*" element={<NotFound />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;