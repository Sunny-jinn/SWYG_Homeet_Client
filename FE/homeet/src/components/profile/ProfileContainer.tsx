import React, { useState } from "react";
import logo from "../../assets/svg/profile.svg";
import ProfileNote from "./ProfileNote";
import ProfileRoomList from "./ProfileRoomList";

const ProfileContainer = () => {
  const [view, setView] = useState<string>("");

  const clickHandler = (curView: string) => {
    setView(curView);
    console.log(view);
  };

  return (
    <div className="profile m-360 flex">
      <div className="profile-left">
        <div className="profile-logo m-360">
          <img src={logo} />
        </div>
        <div className="profile-info">
          <div className="profile-info-name">
            <div className="profile-info-title">이름</div>
            <div className="profile-info-text">김진우</div>
          </div>
          <div className="profile-info-email">
            <div className="profile-info-title">이메일</div>
            <div className="profile-info-text">rlawlsdn316@gmail.com </div>
          </div>
          <div
            className="profile-info-note"
            onClick={() => clickHandler("note")}
          >
            <div
              className={"profile-info-title " + (view === "note" && "checked")}
            >
              쪽지함
            </div>
          </div>
          <div
            className="profile-info-list"
            onClick={() => clickHandler("list")}
          >
            <div
              className={
                "profile-info-title " + (view === "list" && " checked")
              }
            >
              내가 쓴 글
            </div>
          </div>
        </div>
      </div>
      <div className="profile-right">
        {view === "note" && <ProfileNote />}
        {view === "list" && <ProfileRoomList />}
      </div>
    </div>
  );
};

export default ProfileContainer;
