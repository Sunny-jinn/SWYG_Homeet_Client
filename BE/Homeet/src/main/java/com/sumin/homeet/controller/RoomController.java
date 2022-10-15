package com.sumin.homeet.controller;

import com.google.gson.Gson;
import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.dto.MonthRoomDto;
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
    public ResponseEntity<Map<String,String>> registerRoom(@RequestBody Room room,@RequestPart("images") List<MultipartFile> multipartFiles){
        roomService.register(room,multipartFiles);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "success");
        return ResponseEntity.ok().body(msg);
    }
    @GetMapping("/")
    public ResponseEntity<Map<String,Room>> getRoom(@RequestParam("room_id") Long roomId) {
        HashMap<String, Room> data = new HashMap<>();
        Room room = roomService.findOne(roomId);
        data.put("data",room);
        return ResponseEntity.ok().body(data);

    }
    @PutMapping("/update/year")
    public ResponseEntity<Map<String,String>> updateYearRoom(@RequestBody YearRoomDto room){
        roomService.update(room);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "success");
        return ResponseEntity.ok().body(msg);
    }
    @PutMapping("/update/month")
    public ResponseEntity<Map<String,String>> updateMonthRoom(@RequestBody MonthRoomDto room){
        roomService.update(room);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "success");
        return ResponseEntity.ok().body(msg);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,String>> deleteRoom(@RequestParam("room_id") Long roomId){
        roomService.delete(roomId);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "success");
        return ResponseEntity.ok().body(msg);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> allRoom(@RequestParam("location") String location){
        List<Room> roomList = roomService.findAllByLocation(location);

        return
    }



    /**
     * param
     */
    //update

    //delete
}
