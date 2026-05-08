import React from 'react';
import { BrowserRouter, Routes , Route } from "react-router-dom";
import LogIn from './pages/LogIn';
import SignUp from './pages/SignUp';
import FindPassword from './pages/FindPassword.jsx';
import ChangePassword from './pages/ChangePassword.jsx';
import Success from './pages/Success';
import RegisterComponent from './pages/TestSignUp.jsx'


function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
            <Route path ="/" element = {< LogIn/>}/> 
            {/* <Route path ="/log-in" element = {< LogIn/>}/> */}
            <Route path ="/sign-up" element = {<SignUp />}/>
            <Route path ="/find-password" element = {<FindPassword />}/>
            <Route path ="/change-password" element = {<ChangePassword />}/>
            <Route path ="/success" element = {<Success />}/>

            {/* <Route path="*" element={<NotFound />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;