/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject.services.interfaces;

import com.example.sayeretproject.accessingdatajpa.AreaRepository;
import com.example.sayeretproject.accessingdatajpa.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class UserService {
    protected final PointRepository pointRepository;
    protected final AreaRepository areaRepository;
}
