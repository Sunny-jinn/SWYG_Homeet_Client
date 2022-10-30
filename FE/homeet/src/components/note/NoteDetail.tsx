import React, { Dispatch, SetStateAction, useRef } from "react";
import { INoteData } from "../dummy";

interface IProp {
  list: INoteData;
  setIsModal: Dispatch<SetStateAction<boolean>>;
}

const NoteDetail = ({ list, setIsModal }: IProp): JSX.Element => {
  const outsideRef = useRef(null);

  return (
    <div
      className="note-container"
      ref={outsideRef}
      onClick={(e: any) => {
        if (e.target === outsideRef?.current) setIsModal(false);
      }}
    >
      <div className="note-contents">
        {list.noteList.map((list, index) => (
          <div key={index}>
            {list.receiverId === 2 ? (
              <div className="note-content note-right flex">
                <div className="note-date">
                  {new Date(list.createDate).toLocaleDateString()}
                </div>
                <div className="note-content-text-right ">{list.content}</div>
              </div>
            ) : (
              <div className="note-content note-left flex">
                <div className="note-content-text-left ">{list.content}</div>
                <div className="note-date">
                  {new Date(list.createDate).toLocaleDateString()}
                </div>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default NoteDetail;
