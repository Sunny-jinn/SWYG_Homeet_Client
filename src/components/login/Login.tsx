import React from "react";
import axios, { AxiosError, AxiosResponse } from "axios";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useEffect } from "react";
import Loading from "../layout/Loading";
import Header from "../layout/Header";
import { useDispatch } from "react-redux";
import { userActions } from "../../store/user";
import QueryString from "qs";

const Login = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const KAKAO_CODE = location.search.split("=")[1];

  const getToken = async () => {
    const payload = QueryString.stringify({
      grant_type: "authorization_code",
      client_id: process.env.REACT_APP_REST_API_KEY,
      redirect_uri: process.env.REACT_APP_REDIRECT_URI,
      code: KAKAO_CODE,
    });

    try {
      const res = await axios.post(
        "https://kauth.kakao.com/oauth/token",
        payload
      );
      localStorage.setItem("access_token", res.data.access_token);

      axios
        .get(`${process.env.REACT_APP_PORT}/oauth/kakao`, {
          params: {
            token: res.data.access_token,
          },
        })
        .then((res) => {
          localStorage.setItem("nickname", res.data.nickname);
          localStorage.setItem("userId", res.data.userId);
          dispatch(
            userActions.setUserId({
              id: res.data.userId,
              nickname: res.data.nickname,
            })
          );
          navigate("/");
        });
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    getToken();
  }, []);

  return (
    <>
      <Header />
      <Loading />
    </>
  );
};

export default Login;
