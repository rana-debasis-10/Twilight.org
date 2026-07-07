package com.twilight.endPoints;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.services.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Validated
@RequiredArgsConstructor
public class SearchEndpoint {

    private SearchService searchService ;

    @GetMapping("/outlet")
    @Transactional
    public List<OutletR> findNearestOutlet(@RequestParam double lat, @RequestParam double lon ){
        return searchService.findNearestOutlets(lat,lon);
    }
    @GetMapping("/food")
    @Transactional
    public List<FoodR> getFoods(@RequestParam(name = "o")Integer outletId){
        return searchService.getMenuByOutlet(outletId);
    }

}
