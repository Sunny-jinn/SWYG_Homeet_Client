import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";

const MainInput = (): JSX.Element => {
  const navigate = useNavigate();
  const inputRef = useRef<HTMLInputElement>(null);

  const clickHandler = () => {
    navigate(`/search/1${inputRef.current?.value}`);
  };

  return (
    <div className="input-box">
      <input
        ref={inputRef}
        className="input"
        placeholder="원하는 지역을 검색하세요"
      />
      <img
        src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
        onClick={clickHandler}
        alt="searchLogo"
      />
    </div>
  );
};

export default MainInput;
