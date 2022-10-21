import React from "react";
import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import Search from "./pages/Search";

import "./assets/css/index.css";
import "./assets/css/room.css";
import Room from "./pages/Room";

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
        <Route path="/search/:country" element={<Search />} />
        <Route path="/room/detail/:roomId" element={<Room />} />
      </Routes>
    </div>
  );
};

export default App;
