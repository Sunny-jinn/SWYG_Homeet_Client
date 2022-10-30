import { createSlice, configureStore } from "@reduxjs/toolkit";

export interface IModal {
  isModal: boolean;
  isNewModal: boolean;
}

const modalSlice = createSlice({
  name: "modal",
  initialState: {
    isModal: false,
    isNewModal: false,
  },
  reducers: {
    setIsModal(state, action) {
      state.isModal = action.payload;
    },
    setIsNewModal(state, action) {
      state.isNewModal = action.payload;
    },
  },
});

const store = configureStore({
  reducer: {
    modal: modalSlice.reducer,
  },
});

export const modalActions = modalSlice.actions;
export default store;
