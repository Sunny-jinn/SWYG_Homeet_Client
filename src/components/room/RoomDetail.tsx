import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { modalActions } from "../../store/modal";
import { IRoom } from "../../store/room";
import NoteNew from "../note/NoteNew";
import RoomMap from "./RoomMap";

interface IProps {
  roomId: string;
}

const RoomDetail = ({ roomId }: IProps) => {
  const [room, setRoom] = useState<IRoom>();
  const dispatch = useDispatch();
  const roomList = useSelector((state: any) => state.room.roomList);
  const isNewModal = useSelector((state: any) => state.modal.isNewModal);
  dispatch(modalActions.setIsModal(false));

  useEffect(() => {
    const tempList: any = roomList.filter(
      (list: IRoom) => list.id === Number(roomId)
    );
    setRoom(tempList[0]);
  }, []);

  return (
    <>
      {isNewModal && <NoteNew />}
      <div className="room-detail-container m-360">
        <div className="flex room-detail-images">
          <div className="room-detail-img">
            <img src={room?.imageUrl[0]} alt="roomImage" />
          </div>
          <div className="room-detail-img">
            <img src={room?.imageUrl[1]} alt="roomImage" />
          </div>
        </div>
        <div className="room-detail-bottom">
          <p>정보</p>
          <div className="room-detail-info flex">
            <div className="room-detail-info-left">
              <div className="room-detail-info-box flex">
                <div className="room-detail-info-title">위치</div>
                <div className="room-detail-info-text">{room?.location}</div>
              </div>
              {room?.perPrice ? (
                <>
                  <div className="room-detail-info-box flex">
                    <div className="room-detail-info-title">보증금</div>
                    <div className="room-detail-info-text">
                      {room?.perPrice?.toLocaleString()}
                    </div>
                  </div>
                  <div className="room-detail-info-box flex">
                    <div className="room-detail-info-title">월세</div>
                    <div className="room-detail-info-text">
                      {room?.onePrice?.toLocaleString()}
                    </div>
                  </div>
                </>
              ) : (
                <>
                  <div className="room-detail-info-box flex">
                    <div className="room-detail-info-title">전세</div>
                    <div className="room-detail-info-text">
                      {room?.yearPrice?.toLocaleString()}
                    </div>
                  </div>
                </>
              )}
              <div className="room-detail-info-box flex">
                <div className="room-detail-info-title">구조</div>
                <div className="room-detail-info-text">
                  {room?.duplex ? "복층" : "단층"}
                </div>
              </div>
            </div>
            <div className="room-detail-info-right">
              <div className="room-detail-info-box flex">
                <div className="room-detail-info-title">작성자</div>
                <div className="room-detail-info-text">Sunny</div>
              </div>
              <div className="room-detail-info-box flex">
                <div className="room-detail-info-title">연락처</div>
                <div className="room-detail-info-text">
                  <button
                    className="note-button"
                    onClick={() => {
                      dispatch(modalActions.setIsNewModal(true));
                    }}
                  >
                    쪽지 보내기
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div className="room-detail-location">
            <p>위치</p>
            <div className="room-detail-map">
              <RoomMap location={room?.location} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default RoomDetail;
