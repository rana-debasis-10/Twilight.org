package com.twilight.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class OutletOrderRequest implements Serializable {
    List<ItemR> items;
    String mobNo;
    Integer order ;
    Location location;
}
