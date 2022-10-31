import React from "react";
import axios, { AxiosError, AxiosResponse } from "axios";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useEffect } from "react";
import Loading from "../layout/Loading";
import Header from "../layout/Header";
import { useDispatch } from "react-redux";
import { userActions } from "../../store/user";

const Login = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    const KAKAO_CODE = location.search.split("=")[1];
    console.log("Hi");
    axios
      .get("http://172.20.10.9:8000/oauth/kakao", {
        params: {
          code: KAKAO_CODE,
        },
      })
      .then((res: any) => {
        console.log(res);
        dispatch(
          userActions.setUserId({
            id: res.data.userId,
            nickname: res.data.nickname,
            token: res.data.token,
          })
        );
        navigate("/");
      })
      .catch((err: AxiosError) => console.log(err));
  }, []);

  return (
    <>
      <Header />
      <Loading />
    </>
  );
};

export default Login;
