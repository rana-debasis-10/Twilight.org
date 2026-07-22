package com.twilight.dataTransferObjects;

import com.twilight.dataTransferObjects.Location;

import java.io.Serializable;

public record OrderDeliveryRequest ( Location deliverylocation,
                                    Location pickupLocation,
                                    double earning) implements Serializable {}
