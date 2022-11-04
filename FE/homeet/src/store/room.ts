import { createSlice, configureStore } from "@reduxjs/toolkit";
import room from "../assets/img/room.png";
import { IUser } from "../components/dummy";

export interface IRoom {
  room_id: number;
  user_id: string;
  dtype: string; // 월세 M, 전세 Y
  duplex: boolean;
  location: string;
  imageUrl: Array<string>;
  user?: IUser;
  onePrice?: number;
  perPrice?: number;
  yearPrice?: number;
}

export interface IRommList {
  roomList: IRoom[];
}

const initialRoomState = {
  roomList: [
    {
      room_id: 0,
      user_id: "sunny",
      dtype: "전세",
      duplex: true,
      location: "경기도 수원시 영통구 덕영대로 1703",
      imageUrl: [room, room, room, room],
      yearPrice: 120000000,
    },
    {
      room_id: 1,
      user_id: "sumin",
      dtype: "월세",
      duplex: true,
      location: "경기도 수원시 영통구 영일로 16-4",
      onePrice: 420000,
      imageUrl: [room],
      perPrice: 5000000,
    },
    {
      room_id: 2,
      user_id: "seokjin",
      dtype: "월세",
      duplex: false,
      location: "경기도 수원시 영통구 청명로 43번길 15",
      onePrice: 420000,
      imageUrl: [room],
      perPrice: 5000000,
    },
    {
      room_id: 3,
      user_id: "sunny",
      dtype: "월세",
      duplex: false,
      location: "경기도 안양시 동안구 경수대로 883번길 33",
      onePrice: 470000,
      imageUrl: [room],
      perPrice: 5000000,
    },
    {
      room_id: 4,
      user_id: "junpyo",
      dtype: "월세",
      duplex: false,
      location: "경기도 안양시 동안구 엘에스로 45",
      onePrice: 600000,
      imageUrl: [room],
      perPrice: 10000000,
    },
    {
      room_id: 5,
      user_id: "sunho",
      dtype: "월세",
      duplex: false,
      location: "서울특별시 동작구 장승배기로11가길 11",
      onePrice: 450000,
      imageUrl: [room],
      perPrice: 10000000,
    },
  ],
};

export const roomSlice = createSlice({
  name: "room",
  initialState: initialRoomState,
  reducers: {
    setRoomList(state, action) {
      state.roomList = [...state.roomList, action.payload];
    },
  },
});

export const roomActions = roomSlice.actions;
