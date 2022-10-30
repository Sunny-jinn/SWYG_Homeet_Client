import React, { Dispatch, SetStateAction, useState } from "react";
import { INoteData } from "../dummy";

interface IProps {
  index: number;
  list: INoteData;
  setIsModal: Dispatch<SetStateAction<boolean>>;
  setNote: Dispatch<SetStateAction<INoteData>>;
}

const NoteList = ({
  index,
  list,
  setIsModal,
  setNote,
}: IProps): JSX.Element => {
  return (
    <>
      <div
        key={index}
        className="note-item"
        onClick={() => {
          setIsModal(true);
          setNote(list);
        }}
      >
        <p>
          {list.noteForRoomDto.location} /{" "}
          {list.noteForRoomDto.duplex ? "복층" : "단층"}
        </p>

        <div className="note-text">
          <>
            {list.noteList[list.noteList.length - 1].content}
            <div className="note-date">
              {new Date(
                list.noteList[list.noteList.length - 1].createDate
              ).toLocaleDateString()}
            </div>
          </>
        </div>
      </div>
    </>
  );
};

export default NoteList;
