import React from "react";
import MainInput from "./MainInput";

// REACT_APP_REST_API_KEY = c8176843fbae7bbc2ebfe63183c90d2a
// REACT_APP_REDIRECT_URI = http://localhost:8000/oauth/kakao

const KAKAO_URL = `https://kauth.kakao.com/oauth/authorize?client_id=c8176843fbae7bbc2ebfe63183c90d2a&redirect_uri=http://192.168.0.27:8000/oauth/kakao&response_type=code`;

const MainContainer = () => {
  return (
    <div>
      <MainInput />
    </div>
  );
};

export default MainContainer;
