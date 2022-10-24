import React, { ReactElement } from "react";
import downLogo from "../../assets/img/down.png";

const SearchCategory = (): ReactElement | null => {
  return (
    <div className="category-container">
      <div className="category flex m-360">
        <div className="category-location">
          <input placeholder="원하는 지역을 검색하세요" />
          <img
            src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
            alt="searchLogo"
          />
        </div>
        <div className="category-center">
          <div className="category-price flex">
            <input placeholder="최소" />
            <input placeholder="최대" />
          </div>
        </div>
        <div className="category-right">
          <div className="category-d flex">
            <div className="category-duplex relative">
              <select>
                <option>단층</option>
                <option>복층</option>
              </select>
              <img src={downLogo} alt="logo" />
            </div>
            <div className="category-dtype relative">
              <select>
                <option>월세</option>
                <option>전세</option>
              </select>
              <img src={downLogo} alt="logo" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchCategory;
