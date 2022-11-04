import React from "react";
import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import Search from "./pages/Search";
import "./App.css";
import "./assets/css/index.css";
import "./assets/css/room.css";
import "./assets/css/profile.css";
import "./assets/css/note.css";
import Room from "./pages/Room";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import Login from "./components/login/Login";

// 카카오 로그인 api 사용
// declare global {
//   interface Window {
//     Kakao: any;
//   }
// }

const App = () => {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/oauth/kakao" element={<Login />} />
        <Route path="/search/:country" element={<Search />} />
        <Route path="/room/detail/:roomId" element={<Room />} />
        <Route path="/room/register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </div>
  );
};

export default App;
