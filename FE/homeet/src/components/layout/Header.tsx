import React from "react";
import logo from "../../assets/img/icon.png";
import whiteLogo from "../../assets/img/icon_white.png";

const Header = (): JSX.Element => {
  return (
    <header className="m-240">
      <div className="header-logo vertical-mid">
        <img src={whiteLogo} />
      </div>
      <nav className="header-nav vertical-mid inline-block right">
        <ul className="flex">
          <li>로그인</li>
          <li>회원가입</li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
