export {};
// import React, { useRef } from "react";
// import { DUMMY_DATA, IRoom } from "../dummy";
// import axios, { Axios, AxiosError, AxiosResponse } from "axios";

// /* export interface IRoom {
//   room_id: number;
//   user_id: string;
//   dtype: string;
//   duplex: boolean;
//   location: string;
//   one_price?: number;
//   per_price?: number;
//   year_price?: number;
// } */

// const tempData = new FormData();

// const SearchContainer = () => {
//   const inputRef = useRef<HTMLInputElement>(null);

//   const changeHandler = (e: any) => {
//     e.preventDefault();
//     const tempObj = e.target.files;
//     console.log(tempObj);
//     for (let i = 0; i < tempObj.length; i++) {
//       tempData.append("images", tempObj[i]);
//     }
//   };

//   const clickHandler = (e: any) => {
//     tempData.forEach((list: any) => console.log(list));

//     axios
//       .post(`${process.env.REACT_APP_PORT}/room/test/file`, tempData, {
//         headers: {
//           "Content-Type": "multipart/form-data",
//         },
//       })
//       .then((res: AxiosResponse) => console.log(res))
//       .catch((err: AxiosError) => console.log(err));
//   };

//   return (
//     <div>
//       <input type="file" onChange={changeHandler} ref={inputRef} multiple />
//       <button onClick={clickHandler}>fdfd</button>

//       {DUMMY_DATA.map((list: IRoom, index: number) => (
//         <div className="flex" key={index}>
//           <div>{list.room_id}</div>
//           <div>{list.user_id}</div>
//           <div>{list.dtype}</div>
//           <div>{list.duplex}</div>
//           <div>{list.location}</div>
//           <div>{list.year_price}</div>
//         </div>
//       ))}
//     </div>
//   );
// };

// export default SearchContainer;
