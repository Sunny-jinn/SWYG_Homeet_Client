import { useSelector } from "react-redux";
import { IRoom } from "../../store/room";
import RoomCard from "../room/RoomCard";

const SearchContainer = () => {
  const roomList = useSelector((state: any) => state.room.roomList);

  return (
    <div className="flex wrap m-360 room-container">
      {roomList.map((list: IRoom, index: number) => (
        <RoomCard
          key={index}
          room_id={list.room_id}
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
