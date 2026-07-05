package com.twilight.endPoints;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/driver/")
@Validated
@RequiredArgsConstructor
public class DriverEndpoints {

}
