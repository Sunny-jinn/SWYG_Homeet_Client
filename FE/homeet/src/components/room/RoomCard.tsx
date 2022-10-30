import React from "react";
import { Link } from "react-router-dom";
import { IRoom } from "../dummy";

const RoomCard = ({
  room_id,
  user_id,
  dtype,
  duplex,
  location,
  imageUrl,
  onePrice,
  perPrice,
  yearPrice,
}: IRoom): JSX.Element => {
  return (
    <div className="room-card">
      <div className="room-card-container">
        <div className="room-card-inner">
          <Link to={`/room/detail/${room_id}`}>
            <div className="room-image">
              <img src={imageUrl[0]} alt="room" />
            </div>
          </Link>
          <div className="room-text">
            <div className="room-dtype">
              {dtype === "월세" ? (
                <>
                  {dtype} {perPrice! / 10000} / {onePrice! / 10000}
                </>
              ) : (
                <>
                  {dtype} {yearPrice! / 10000}
                </>
              )}
            </div>
            <div className="room-location">{location}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RoomCard;
