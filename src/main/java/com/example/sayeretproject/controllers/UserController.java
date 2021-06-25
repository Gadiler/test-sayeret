/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.sayeretproject.controllers;

import com.example.sayeretproject.exceptions.ExistException;
import com.example.sayeretproject.helper.ExcelHelper;
import com.example.sayeretproject.services.interfaces.AreaService;
import com.example.sayeretproject.services.interfaces.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final PointService pointService;
    private final AreaService areaService;

    @GetMapping(path = "/getAllPoints", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllPoints() throws ExistException {
        return new ResponseEntity<>(this.pointService.getAllPoints(), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getAllAreas")//, produces = {MediaType.APPLICATION_JSON_VALUE}
    public ResponseEntity<?> getAllAreas() throws ExistException {
        return new ResponseEntity<>(this.areaService.getAllAreas(), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getPointById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getPointById(@PathVariable int id) throws ExistException {
        return new ResponseEntity<>(this.pointService.getPointById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getPointByName/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getPointById(@PathVariable String name) throws ExistException {
        return new ResponseEntity<>(this.pointService.getPointByName(name), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getAreaById/{areaId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAreaById(@PathVariable int areaId) throws ExistException {
        return new ResponseEntity<>(this.areaService.getAreaById(areaId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                pointService.save(file);
                areaService.save();
                sync();
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
            }
        }
        message = "Please upload an excel file!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    public void sync() throws ExistException {
        this.pointService.getAllPoints().forEach(point ->{
            try {
                this.areaService.getAreaById(point.getAreaId()).getPoints().add(point);
                this.areaService.updateArea(this.areaService.getAreaById(point.getAreaId()));
            } catch (ExistException e) {
                e.printStackTrace();
            }
        });
    }
}
