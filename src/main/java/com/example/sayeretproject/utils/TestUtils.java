/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject.utils;

import org.springframework.stereotype.Component;

@Component
public class TestUtils {
    private StringBuilder sb;

    public void printTestHeader(String header) {
        sb = new StringBuilder();
        sb.append("----------------------- ").append(header).append(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        sb.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(sb);
    }
}
