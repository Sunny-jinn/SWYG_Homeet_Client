import React from "react";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/img/icon.png";
import whiteLogo from "../../assets/img/icon_white.png";

const Header = (): JSX.Element => {
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${process.env.REACT_APP_REDIRECT_URI}&response_type=code`;
  const navigate = useNavigate();

  const logoClickHandler = () => {
    navigate("/");
  };

  const profileClickHandler = () => {
    navigate("/profile");
  };

  const registerClickHandler = () => {
    navigate("/room/register");
  };

  return (
    <header className="m-240">
      <div className="header-logo vertical-mid">
        <img onClick={logoClickHandler} src={whiteLogo} alt="logo" />
      </div>
      <nav className="header-nav vertical-mid inline-block right">
        <ul className="flex">
          {/* <a href={KAKAO_AUTH_URL}>
            <li>로그인</li>
          </a> */}
          <li onClick={profileClickHandler}>내 정보</li>
          <li onClick={registerClickHandler}>등록하기</li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
