import { listenerCount } from "process";
import React, { ChangeEvent, useState } from "react";
import uploadLogo from "../../assets/svg/image_upload.svg";

const RoomRegister = (): JSX.Element => {
  const [dtype, setDtype] = useState<string>("월세");
  const [roomImg, setRoomImg] = useState<any>([]);
  const [imageUrl, setImageUrl] = useState<any>([]);

  const imageChangeHandler = (e: any) => {
    const formData = new FormData();
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
            <input type="text" />
          </div>
          <div className="room-register-radio">
            <div className="room-register-info flex">
              <p>단층</p>
              <input type="radio" name="duplex" value="단층" defaultChecked />
              <p>복층</p>
              <input type="radio" name="duplex" value="복층" />
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
              <input type="text" />
            </div>
          ) : (
            <>
              <div className="room-register-info">
                <p>보증금 가격</p>
                <input type="text" />
              </div>
              <div className="room-register-info">
                <p>월세 가격</p>
                <input type="text" />
              </div>
            </>
          )}
          <div className="room-register-button">
            <button>등록하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RoomRegister;
