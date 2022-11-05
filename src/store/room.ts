import { createSlice, configureStore } from "@reduxjs/toolkit";
import room from "../assets/img/room.png";
import { IUser } from "../components/dummy";

export interface IRoom {
  id: number;
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
  roomList: [],
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
