import { createSlice } from "@reduxjs/toolkit";

export interface IUser {
  userId: number;
  nickname: string;
  token: string;
}

export const userSlice = createSlice({
  name: "user",
  initialState: {
    userId: 0,
    nickname: "",
    token: "",
  },
  reducers: {
    setUserId(state, action) {
      state.userId = action.payload.id;
      state.nickname = action.payload.nickname;
      state.token = action.payload.token;
    },
  },
});

export const userActions = userSlice.actions;
