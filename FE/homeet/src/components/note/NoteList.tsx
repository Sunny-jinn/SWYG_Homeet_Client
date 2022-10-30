import React, { Dispatch, SetStateAction, useState } from "react";
import { useDispatch } from "react-redux";
import { modalActions } from "../../store/modal";
import { INoteData } from "../dummy";

interface IProps {
  index: number;
  list: INoteData;

  setNote: Dispatch<SetStateAction<INoteData>>;
}

const NoteList = ({ index, list, setNote }: IProps): JSX.Element => {
  const dispatch = useDispatch();

  return (
    <div
      key={index}
      className="note-item"
      onClick={() => {
        dispatch(modalActions.setIsModal(true));
        setNote(list);
      }}
    >
      <p>{list.noteForRoomDto.location}</p>

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
  );
};

export default NoteList;
