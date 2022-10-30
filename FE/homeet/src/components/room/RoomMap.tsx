import React, { useEffect } from "react";

const kakao = (window as any).kakao;

const RoomMap = (): JSX.Element => {
  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.624915253753194, 127.15122688059974),
      level: 3,
    };

    const markerPosition = new kakao.maps.LatLng(
      37.62197524055062,
      127.16017523675508
    );

    const map = new kakao.maps.Map(container, options);

    const imgSrc =
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    const imgSize = new kakao.maps.Size(24, 35);
    const markerImg = new kakao.maps.MarkerImage(imgSrc, imgSize);

    const marker = new kakao.maps.Marker({
      map: map,
      position: markerPosition,
      title: "야호",
      image: markerImg,
    });
    const infowindow = new kakao.maps.InfoWindow({
      content:
        '<div style="width:150px;text-align:center;padding:6px 0;">방 위치</div>',
    });
    infowindow.open(map, marker);
  }, []);

  return (
    <div>
      <div id="map" style={{ width: "900px", height: "600px" }}></div>
    </div>
  );
};

export default RoomMap;
