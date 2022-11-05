import React from "react";
import { Link } from "react-router-dom";
import { IRoom } from "../../store/room";

const RoomCard = ({
  id,
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
          <Link to={`/room/detail/${id}`}>
            <div className="room-image">
              <img src={imageUrl[0]} alt="room" />
            </div>
          </Link>
          <div className="room-text">
            <div className="room-dtype">
              {dtype === "M" ? (
                <>
                  월세 {perPrice! / 10000} / {onePrice! / 10000}
                </>
              ) : (
                <>전세 {yearPrice! / 10000}</>
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
