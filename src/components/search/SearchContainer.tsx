import axios, { AxiosResponse } from "axios";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { IRoom, roomActions } from "../../store/room";
import RoomCard from "../room/RoomCard";

const SearchContainer = () => {
  const roomList = useSelector((state: any) => state.room.roomList);
  const dispatch = useDispatch();
  const params = useParams();
  const token = localStorage.getItem("access_token");
  console.log(token);

  useEffect(() => {
    console.log(params.country);
    axios
      .get(`http://www.homeet.shop/room/all`, {
        params: {
          location: params.country,
        },
        headers: {
          "X-AUTH-TOKEN": token,
        },
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        res.data.data.map((item) => dispatch(roomActions.setRoomList(item)));
      });
  }, []);

  return (
    <div className="flex wrap m-360 room-container">
      {roomList.map((list: IRoom, index: number) => (
        <RoomCard
          key={index}
          id={list.id}
          user_id={list.user_id}
          dtype={list.dtype}
          duplex={list.duplex}
          location={list.location}
          user={list.user}
          imageUrl={list.imageUrl}
          onePrice={list.onePrice}
          perPrice={list.perPrice}
          yearPrice={list.yearPrice}
        />
      ))}
    </div>
  );
};

export default SearchContainer;
