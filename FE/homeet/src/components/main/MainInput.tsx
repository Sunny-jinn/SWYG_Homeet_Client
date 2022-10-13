import React from "react";
import { useNavigate } from "react-router-dom";

const MainInput = (): JSX.Element => {
  const navigate = useNavigate();

  return (
    <div className="input-box">
      <input className="input" placeholder="원하는 지역을 검색하세요" />
      <img
        src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
        onClick={() => {
          navigate("/search");
        }}
        alt="logo"
      />
    </div>
  );
};

export default MainInput;
