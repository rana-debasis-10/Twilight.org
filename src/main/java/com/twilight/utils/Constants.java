package com.twilight.utils;

public class Constants {
    public static final double EARTH_RADIUS_KM = 6371.0;
    public static final double PER_KIL0METER_DELIVERY_CHARGE = 10;
    public static final double BASE_CHARGE_AND_PLATFORM_FEE = 25;
    public static final double MAXIMUM_DELIVERABLE_DISTANCE = 7;
    public static final int MAX_OUTLET_FILTER_LIMIT = 50;
    public final static int OUTLET_DRIVER_MAX_DISTANCE = 3;

    public final static String ROLE = "role";

    public final static String DRIVER_LOCATION = "driver-location";

    public final static String UPDATE_MENU_TOPIC = "menu-update";
    public final static String UPDATE_MENU_LISTENER = "menu-update_listener";

    public final static String INVITATION_EXPIRATION_TOPIC = "invitation-expiration";
    public final static String INVITATION_EXPIRATION_LISTENER = "invitation-expiration-listener";

    public final static String NEW_ORDER_COD_TOPIC = "new-order";
    public final static String NEW_ORDER_COD_LISTENER = "new-order-listener";

    public final static String WEB_SOCKET_NEW_ORDER_MESSAGE = "new-order";

    public final static String ORDER_STARTED_PREPARING = "order-started-preparing";

    public final static String ORDER_REJECTED = "order-rejected";


    public static final String ASSIGN_DELIVERY_PARTNER_TOPIC = "assign-delivery-partner";
    public static final String ASSIGN_DELIVERY_PARTNER_LISTENER = "assign-delivery-partner-listener";
}
