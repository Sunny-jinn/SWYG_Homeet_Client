import React, { useEffect, useRef } from "react";

const kakao = (window as any).kakao;

interface IProps {
  location: string;
}

const RoomMap = ({ location }: IProps): JSX.Element => {
  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);

    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(location, (data, status, pagination) => {
      if (status === kakao.maps.services.Status.OK) {
        let bounds = new kakao.maps.LatLngBounds();

        displayMarker2(data[0]);
        bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
        ps.keywordSearch(location + "부동산", (data, status, pagination) => {
          if (status === kakao.maps.services.Status.OK) {
            let bounds = new kakao.maps.LatLngBounds();

            for (let i = 0; i < data.length; i++) {
              displayMarker(data[i]);
              bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }
          }
        });

        map.setBounds(bounds);
      }
    });

    const displayMarker = (place) => {
      let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });
      let infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출
        infowindow.setContent(
          '<div style="width: 150px;padding:10px;font-size:1rem;text-align: center; ">' +
            place.place_name +
            "</div>"
        );
        infowindow.open(map, marker);
      });
    };
    const displayMarker2 = (place) => {
      const imgSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImg = new kakao.maps.MarkerImage(imgSrc, imgSize);
      let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        title: "방 위치",
        image: markerImg,
      });

      let infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출
        infowindow.setContent(
          '<div style="width: 150px;padding:10px;font-size:1rem;text-align: center; ">' +
            place.place_name +
            "</div>"
        );
        infowindow.open(map, marker);
      });
    };
  }, [location]);

  return (
    <div>
      <div id="map" style={{ width: "900px", height: "600px" }}></div>
    </div>
  );
};

export default RoomMap;
