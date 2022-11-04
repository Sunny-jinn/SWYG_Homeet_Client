import React, { ChangeEvent, ReactElement, useState } from "react";
import downLogo from "../../assets/img/down.png";

const SearchCategory = (): ReactElement | null => {
  const [minPrice, setMinPrice] = useState<string>("");
  const [maxPrice, setMaxPrice] = useState<string>("");

  const minChangeHandler = (e: ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    const tempValue = e.target.value;
    const removedComma: number = Number(tempValue.replaceAll(",", ""));
    setMinPrice(removedComma.toLocaleString());
  };

  const maxChangeHandler = (e: ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    const tempValue = e.target.value;
    const removedComma: number = Number(tempValue.replaceAll(",", ""));
    setMaxPrice(removedComma.toLocaleString());
  };

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
            <input
              type="text"
              value={minPrice}
              placeholder="최소"
              onChange={minChangeHandler}
            />
            <input
              type="text"
              value={maxPrice}
              placeholder="최대"
              onChange={maxChangeHandler}
            />
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
