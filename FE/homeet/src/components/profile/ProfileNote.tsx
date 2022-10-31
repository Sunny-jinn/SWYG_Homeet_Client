import React, { useState } from "react";
import { useSelector } from "react-redux";
import { DUMMY_NOTE, INoteData } from "../dummy";
import NoteDetail from "../note/NoteDetail";
import NoteList from "../note/NoteList";
import NoteNew from "../note/NoteNew";

const ProfileNote = () => {
  const isModal = useSelector((state: any) => state.modal.isModal);
  const [note, setNote] = useState<INoteData>();
  const allNoteList = useSelector((state: any) => state.note.allNoteList);
  const isNewModal = useSelector((state: any) => state.modal.isNewModal);

  return (
    <>
      {isModal && <NoteDetail list={note!} />}
      {isNewModal && <NoteNew />}

      <div className="profile-note">
        <p>쪽지함</p>
        <div className="profile-note-container">
          <div className="profile-note-list">
            {allNoteList.map((list: INoteData, index: number) => (
              <NoteList list={list} index={index} setNote={setNote} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default ProfileNote;
