import React, { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { INoteData } from "../dummy";
import { modalActions } from "../../store/modal";

interface IProp {
  list: INoteData;
}

const NoteDetail = ({ list }: IProp): JSX.Element => {
  const outsideRef = useRef(null);
  const dispatch = useDispatch();

  return (
    <div
      className="note-container"
      ref={outsideRef}
      onClick={(e: any) => {
        if (e.target === outsideRef?.current)
          dispatch(modalActions.setIsModal(false));
      }}
    >
      <div className="note-contents">
        <div className="note-contents-title">
          {list.noteForRoomDto.location}
        </div>
        <div className="note-contents-list">
          {list.noteList.map((list, index) => (
            <div key={index}>
              {list.receiverId === 2 ? (
                <div className="note-content note-right flex ">
                  <div className="note-content-date-right">
                    <div className="align-bottom">
                      {new Date(list.createDate).toLocaleDateString()}
                    </div>
                  </div>
                  <div className="note-content-text-right ">{list.content}</div>
                </div>
              ) : (
                <div className="note-content note-left  flex">
                  <div className="note-content-text-left ">{list.content}</div>
                  <div className="note-content-date-left">
                    <div className="align-bottom">
                      {new Date(list.createDate).toLocaleDateString()}
                    </div>
                  </div>
                </div>
              )}
            </div>
          ))}
        </div>
        <div className="note-button profile-note-button">
          <button
            onClick={() => {
              dispatch(modalActions.setIsModal(false));
              dispatch(modalActions.setIsNewModal(true));
              console.log("zz");
            }}
          >
            쪽지 보내기
          </button>
        </div>
      </div>
    </div>
  );
};

export default NoteDetail;
