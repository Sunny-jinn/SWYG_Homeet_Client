import React, { useRef } from "react";
import { useDispatch } from "react-redux";
import { modalActions } from "../../store/modal";
const NoteNew = () => {
  const outsideRef = useRef(null);
  const dispatch = useDispatch();
  return (
    <div
      className="note-container"
      ref={outsideRef}
      onClick={(e: any) => {
        if (e.target === outsideRef?.current)
          dispatch(modalActions.setIsNewModal(false));
      }}
    >
      <div className="note-contents-new">
        <textarea></textarea>
        <div
          className="note-button"
          onClick={() => {
            dispatch(modalActions.setIsNewModal(false));
          }}
        >
          <button>전송</button>
        </div>
      </div>
    </div>
  );
};

export default NoteNew;
