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
    token: false,
  },
  reducers: {
    setUserId(state, action) {
      state.userId = action.payload.id;
      state.nickname = action.payload.nickname;
    },
    setToken(state, action) {
      state.token = action.payload;
    },
  },
});

export const userActions = userSlice.actions;
