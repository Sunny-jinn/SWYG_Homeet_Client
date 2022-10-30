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
        if (e.target == outsideRef?.current) setIsModal(false);
      }}
    >
      <div className="note-content">{list.noteForRoomDto.id}</div>
    </div>
  );
};

export default NoteDetail;
