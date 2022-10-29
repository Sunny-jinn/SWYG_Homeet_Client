import room from "../assets/img/room.png";

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

export interface IUser {
  email: string;
  nickname: string;
  userId: number;
}

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

export const DUMMY_DATA: IRoom[] = [
  {
    room_id: 0,
    user_id: "sunny",
    dtype: "전세",
    duplex: true,
    location: "경기도 수원시 영통구",
    imageUrl: [room, room, room, room],
    yearPrice: 120000000,
  },
  {
    room_id: 1,
    user_id: "sumin",
    dtype: "월세",
    duplex: true,
    location: "경기도 수원시 영통구",
    onePrice: 420000,
    imageUrl: [room],
    perPrice: 5000000,
  },
  {
    room_id: 2,
    user_id: "seokjin",
    dtype: "월세",
    duplex: false,
    location: "경기도 수원시 팔달구",
    onePrice: 420000,
    imageUrl: [room],
    perPrice: 5000000,
  },
  {
    room_id: 3,
    user_id: "sunny",
    dtype: "월세",
    duplex: false,
    location: "경기도 안양시 동안구",
    onePrice: 470000,
    imageUrl: [room],
    perPrice: 5000000,
  },
  {
    room_id: 4,
    user_id: "junpyo",
    dtype: "월세",
    duplex: false,
    location: "경기도 안양시 동안구",
    onePrice: 600000,
    imageUrl: [room],
    perPrice: 10000000,
  },
];

export const DUMMY_NOTE: Array<INoteData> = [
  {
    noteList: [
      {
        id: 1,
        content: "처음안녕하세요",
        receiverId: 1,
        createDate: "2022-10-21T00:21:15",
        senderId: 2,
      },
      {
        id: 2,
        content: "나중안녕하세요",
        receiverId: 2,
        createDate: "2022-10-21T00:21:17",
        senderId: 1,
      },
      {
        id: 3,
        content: "마지막안녕하세요",
        receiverId: 1,
        createDate: "2022-10-23T00:21:30",
        senderId: 2,
      },
    ],
    noteForRoomDto: {
      id: 1,
      location: "경기도 수원시 영통구 영통동",
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
      location: "경기도 안양시 동안구",
      duplex: false,
    },
  },
];
