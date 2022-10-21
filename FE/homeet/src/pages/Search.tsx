import React from "react";
import Header from "../components/layout/Header";
import SearchCategory from "../components/search/SearchCategory";
import SearchContainer from "../components/search/SearchContainer";

const Search = () => {
  return (
    <div>
      <Header />
      <SearchCategory />
      <SearchContainer />
    </div>
  );
};

export default Search;
