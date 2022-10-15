package com.sumin.homeet.controller;

import com.google.gson.Gson;
import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.dto.MonthRoomDto;
import com.sumin.homeet.dto.RoomDto;
import com.sumin.homeet.dto.YearRoomDto;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.s3.AwsS3Service;
import com.sumin.homeet.service.RoomService;
import com.sumin.homeet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final UserService userService;
    private final AwsS3Service awsS3Service;
    private final RoomService roomService;
    //get

    //register
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerRoom(@RequestPart("data") RoomDto room,@RequestPart("images") List<MultipartFile> multipartFiles){
        roomService.register(room,multipartFiles);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "register success");
        return ResponseEntity.ok().body(msg);
    }
    @GetMapping("/")
    public ResponseEntity<Map<String,RoomDto>> getRoom(@RequestParam("room_id") Long roomId,
                                                    @RequestParam("dtype") String dtype) {
        Map<String, RoomDto> data = new HashMap<>();
        RoomDto room = roomService.findOne(roomId,dtype);
        data.put("data",room);
        return ResponseEntity.ok().body(data);

    }
    @PutMapping("/update/year")
    public ResponseEntity<Map<String,String>> updateYearRoom(@RequestBody YearRoomDto room){
        roomService.update(room);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "update success");
        return ResponseEntity.ok().body(msg);
    }
    @PutMapping("/update/month")
    public ResponseEntity<Map<String,String>> updateMonthRoom(@RequestBody MonthRoomDto room){
        roomService.update(room);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "update success");
        return ResponseEntity.ok().body(msg);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,String>> deleteRoom(@RequestParam("room_id") Long roomId){
        roomService.delete(roomId);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "delete success");
        return ResponseEntity.ok().body(msg);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<RoomDto>>> allRoom(@RequestParam("location") String location){
        List<RoomDto> roomList = roomService.findAllByLocation(location);
        Map<String, List<RoomDto>> data = new HashMap<>();
        data.put("data",roomList);
        return ResponseEntity.ok().body(data);
    }



    /**
     * param
     */
    //update

    //delete
}
