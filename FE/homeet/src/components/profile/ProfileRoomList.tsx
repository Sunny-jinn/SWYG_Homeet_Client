import React from "react";
import { useNavigate } from "react-router-dom";
import { DUMMY_DATA, IRoom } from "../dummy";

const ProfileRoomList = () => {
  const navigate = useNavigate();

  return (
    <div className="profile-roomlist">
      <p>내가 쓴 글</p>
      <div className="profile-note-container">
        <div className="profile-note-list">
          {DUMMY_DATA.map((list: IRoom, index: number) => (
            <div
              key={index}
              className="note-item"
              onClick={() => {
                navigate(`/room/detail/${list.room_id}`);
              }}
            >
              <p>
                {list.dtype}{" "}
                {list.dtype === "전세" ? (
                  <>{list.yearPrice.toLocaleString()}</>
                ) : (
                  <>
                    {list.perPrice.toLocaleString()}
                    {" / "}
                    {list.onePrice.toLocaleString()}
                  </>
                )}{" "}
              </p>

              <div className="note-text">{list.location}</div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProfileRoomList;
