import axios from "axios";
import React, { useEffect, useState } from "react";
import { DUMMY_DATA, IRoom } from "../dummy";

interface IProps {
  roomId: string;
}

/* export interface IRoom {
  room_id: number;
  user_id: string;
  dtype: string; // 월세 M, 전세 Y
  duplex: boolean;
  location: string;
  image_url: string;
  one_price?: number;
  per_price?: number;
  year_price?: number;
}*/

const RoomDetail = ({ roomId }: IProps) => {
  const [room, setRoom] = useState<IRoom>();

  useEffect(() => {
    const tempList: any = DUMMY_DATA.filter(
      (list: IRoom) => list.room_id === Number(roomId)
    );
    setRoom(tempList[0]);

    axios
      .get("http://192.168.0.27:8000/oauth/kakao")
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      {room?.location} {room?.dtype}
    </div>
  );
};

export default RoomDetail;
