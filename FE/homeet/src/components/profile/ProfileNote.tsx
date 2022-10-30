import React, { useState } from "react";
import { DUMMY_NOTE, INoteData } from "../dummy";
import NoteDetail from "../note/NoteDetail";
import NoteList from "../note/NoteList";

const ProfileNote = () => {
  const [isModal, setIsModal] = useState<boolean>(false);
  const [note, setNote] = useState<INoteData>();

  return (
    <div className="profile-note">
      <p>쪽지함</p>
      {isModal && <NoteDetail list={note!} setIsModal={setIsModal} />}
      <div className="profile-note-container">
        <div className="profile-note-list">
          {DUMMY_NOTE.map((list: INoteData, index: number) => (
            <NoteList
              list={list}
              index={index}
              setIsModal={setIsModal}
              setNote={setNote}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProfileNote;
