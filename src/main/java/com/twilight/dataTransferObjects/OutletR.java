package com.twilight.dataTransferObjects;

import com.twilight.types.OutletStatus;

public interface OutletR {
        Integer getId();
        String getName();
        String getImage() ;
        OutletStatus getStatus();
        Double getLatitude();
        Double getLongitude();
}
