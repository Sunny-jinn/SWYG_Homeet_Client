import { createSlice } from "@reduxjs/toolkit";

export interface INote {
  id: number;
  content: string;
  receiverId: number;
  createDate: string;
  senderId: number;
}

export interface INoteDto {
  id: number;
  location: string;
  duplex: boolean;
}

export interface INoteData {
  noteList: INote[];
  noteForRoomDto: INoteDto;
}

interface INoteTotal {
  noteTotal: INoteData[];
}

const initialNoteState = {
  allNoteList: [
    {
      noteList: [
        {
          id: 1,
          content: "처음 안녕하세요",
          receiverId: 1,
          createDate: "2022-10-21T00:21:15",
          senderId: 2,
        },
        {
          id: 2,
          content:
            "처음 안녕하세요 처음 안녕하세요 처음 안녕하세요 처음 안녕하세요",
          receiverId: 1,
          createDate: "2022-10-21T00:21:15",
          senderId: 2,
        },
        {
          id: 3,
          content:
            "나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 ",
          receiverId: 2,
          createDate: "2022-10-21T00:21:17",
          senderId: 1,
        },
        {
          id: 4,
          content: "마지막 안녕하세요",
          receiverId: 1,
          createDate: "2022-10-23T00:21:30",
          senderId: 2,
        },
        {
          id: 3,
          content:
            "나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 ",
          receiverId: 2,
          createDate: "2022-10-21T00:21:17",
          senderId: 1,
        },
        {
          id: 4,
          content: "마지막 안녕하세요",
          receiverId: 1,
          createDate: "2022-10-23T00:21:30",
          senderId: 2,
        },
        {
          id: 3,
          content:
            "나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 나중 안녕하세요 ",
          receiverId: 2,
          createDate: "2022-10-21T00:21:17",
          senderId: 1,
        },
        {
          id: 4,
          content: "마지막 안녕하세요",
          receiverId: 1,
          createDate: "2022-10-23T00:21:30",
          senderId: 2,
        },
      ],
      noteForRoomDto: {
        id: 1,
        location: "경기도 수원시 영통구 청명로 43번길 15",
        duplex: true,
      },
    },
    {
      noteList: [
        {
          id: 1,
          content: "Hello",
          receiverId: 1,
          createDate: "2022-10-21T00:21:15",
          senderId: 2,
        },
        {
          id: 2,
          content: "Goodbye",
          receiverId: 2,
          createDate: "2022-10-21T00:21:17",
          senderId: 1,
        },
      ],
      noteForRoomDto: {
        id: 2,
        location: "경기도 안양시 동안구 경수대로 883번길 33",
        duplex: false,
      },
    },
  ],
};

export const noteSlice = createSlice({
  name: "note",
  initialState: initialNoteState,
  reducers: {
    setNoteList(state, action) {
      state.allNoteList = [...state.allNoteList, action.payload];
    },
  },
});

export const noteActions = noteSlice.actions;
