/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject.services.interfaces;

import com.example.sayeretproject.beans.Area;
import com.example.sayeretproject.exceptions.ExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AreaService {
//    Area getAreaByName(String areaName) throws ExistException;
    Area getAreaById(int areaId) throws ExistException;
    void save();
    void updateArea(Area area);
    List<Area> getAllAreas();
}
