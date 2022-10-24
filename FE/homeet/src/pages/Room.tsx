import React from "react";
import Header from "../components/layout/Header";
import RoomDetail from "../components/room/RoomDetail";
import { useParams } from "react-router-dom";
import SearchCategory from "../components/search/SearchCategory";

const Room = () => {
  const { roomId } = useParams();
  return (
    <div>
      <Header />
      <RoomDetail roomId={roomId!} />
    </div>
  );
};

export default Room;
