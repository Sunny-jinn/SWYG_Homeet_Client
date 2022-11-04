import React from "react";
import logo from "../../assets/img/homeet_logo.png";

const Loading = (): JSX.Element => {
  return (
    <div className="loading">
      <img src={logo} />
    </div>
  );
};

export default Loading;
