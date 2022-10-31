import { configureStore } from "@reduxjs/toolkit";
import { modalSlice } from "./modal";
import { noteSlice } from "./note";
import { roomSlice } from "./room";
import { userSlice } from "./user";

const store = configureStore({
  reducer: {
    modal: modalSlice.reducer,
    user: userSlice.reducer,
    room: roomSlice.reducer,
    note: noteSlice.reducer,
  },
});

export default store;
