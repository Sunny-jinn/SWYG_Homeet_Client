import React from "react";
import { Routes, Route } from "react-router-dom";
import "./assets/css/index.css";
import "./App.css";

import Main from "./pages/Main";
import Search from "./pages/Search";

declare global {
  interface Window {
    Kakao: any;
  }
}

const App = () => {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/search" element={<Search />} />
      </Routes>
    </div>
  );
};

export default App;
