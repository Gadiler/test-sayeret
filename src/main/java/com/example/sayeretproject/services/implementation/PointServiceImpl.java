/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject.services.implementation;

import com.example.sayeretproject.accessingdatajpa.AreaRepository;
import com.example.sayeretproject.accessingdatajpa.PointRepository;
import com.example.sayeretproject.beans.Point;
import com.example.sayeretproject.exceptions.ExistException;
import com.example.sayeretproject.helper.ExcelHelper;
import com.example.sayeretproject.services.interfaces.PointService;
import com.example.sayeretproject.services.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PointServiceImpl extends UserService implements PointService {
    private final String TAG = this.getClass().getSimpleName();

    public PointServiceImpl(PointRepository pointRepository, AreaRepository areaRepository) {
        super(pointRepository, areaRepository);
    }

    @Override
    public List<Point> getAllPoints() {
        return this.pointRepository.findAll();
    }

    @Override
    public Point getPointById(int pointId) throws ExistException {
        return this.pointRepository.findById(pointId).orElseThrow(() -> new ExistException("The point id: " + pointId + " doesn't exist!"));
    }

    @Override
    public Point getPointByName(String name) throws ExistException {
        Point p = this.pointRepository.findByName(name);
        if (p != null)
            return p;
        else
            throw new ExistException("The point name: " + name + " doesn't exist!");
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Point> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            pointRepository.saveAll(tutorials);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
