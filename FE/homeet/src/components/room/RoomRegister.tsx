import axios, { AxiosError, AxiosResponse } from "axios";
import React, { ChangeEvent, useState } from "react";
import { useRef } from "react";
import { useSelector } from "react-redux";
import uploadLogo from "../../assets/svg/image_upload.svg";

const RoomRegister = (): JSX.Element => {
  const [dtype, setDtype] = useState<string>("월세");
  const [duplex, setDuplex] = useState<string>("단층");
  const [roomImg, setRoomImg] = useState<any>([]);
  const [imageUrl, setImageUrl] = useState<any>([]);
  const token = useSelector((state: any) => state.user.token);

  const locationRef = useRef(null);
  const formData = new FormData();

  const perRef = useRef(null);
  const oneRef = useRef(null);
  const yearRef = useRef(null);

  const clickHandler = () => {
    console.log(locationRef.current?.value);
    console.log(perRef.current?.value);
    console.log(oneRef.current?.value);
    console.log(yearRef.current?.value);
    console.log(duplex);
    console.log(dtype);
    console.log(roomImg);
    formData.append("dtype", dtype === "전세" ? "Y" : "M");
    formData.append("location", locationRef.current?.value);
    formData.append("content", " ");
    formData.append("duplex", duplex);
    formData.append("onePrice", oneRef.current?.value);
    formData.append("perPrice", perRef.current?.value);
    formData.append("yearPrice", yearRef.current?.value);

    axios
      .post(
        `http://172.20.10.9:8000/room/register`,
        {
          data: formData,
        },
        {
          headers: {
            "Content-Type": "multipart/form-data",
            "X-AUTH-TOKEN": token,
          },
        }
      )
      .then((res: AxiosResponse) => console.log(res))
      .catch((err: AxiosError) => console.log(err));
  };

  const imageChangeHandler = (e: any) => {
    for (let i = 0; i < e.target.files.length; i++) {
      formData.append("images", e.target.files[i]);
      setImageUrl((imageUrl: any) => [
        ...imageUrl,
        URL.createObjectURL(e.target.files[i]),
      ]);
    }
    setRoomImg(formData);
  };

  const dtypeChangeHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setDtype(e.target?.value);
  };

  const duplexChangeHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setDuplex(e.target?.value);
  };

  return (
    <div className="room-register">
      <div className="room-register-container m-360 flex">
        <div className="room-register-left">
          <>
            <p>방 사진</p>
            <label htmlFor="image">
              <div className="room-register-imgs flex">
                <div>
                  <input
                    id="image"
                    type="file"
                    multiple
                    style={{ display: "none" }}
                    onChange={imageChangeHandler}
                  />
                  <img src={uploadLogo} alt="이미지 업로드" />
                </div>
              </div>
            </label>
          </>
          <div className="room-register-thumbimgs flex">
            {imageUrl.map((list: any, key: any) => (
              <div key={key} className="room-register-thumbimgs-list">
                <img src={list} />
              </div>
            ))}
          </div>
        </div>
        <div className="room-register-right">
          <div className="room-register-info">
            <p>주소</p>
            <input ref={locationRef} type="text" />
          </div>
          <div className="room-register-radio">
            <div className="room-register-info flex">
              <p>단층</p>
              <input
                type="radio"
                name="duplex"
                value="단층"
                onChange={duplexChangeHandler}
                defaultChecked
              />
              <p>복층</p>
              <input
                type="radio"
                name="duplex"
                value="복층"
                onChange={duplexChangeHandler}
              />
            </div>
            <div className="room-register-info flex ">
              <p>월세</p>
              <input
                type="radio"
                name="dtype"
                value="월세"
                onChange={dtypeChangeHandler}
                defaultChecked
              />
              <p>전세</p>
              <input
                type="radio"
                name="dtype"
                value="전세"
                onChange={dtypeChangeHandler}
              />
            </div>
          </div>
          {dtype === "전세" ? (
            <div className="room-register-info">
              <p>전세 가격</p>
              <input ref={yearRef} type="text" />
            </div>
          ) : (
            <>
              <div className="room-register-info">
                <p>보증금 가격</p>
                <input ref={perRef} type="text" />
              </div>
              <div className="room-register-info">
                <p>월세 가격</p>
                <input ref={oneRef} type="text" />
              </div>
            </>
          )}
          <div className="room-register-button">
            <button onClick={clickHandler}>등록하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RoomRegister;
