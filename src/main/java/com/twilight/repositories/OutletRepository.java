package com.twilight.repositories;

import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Outlet;
import com.twilight.objects.OutletLocation;
import com.twilight.types.OutletStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OutletRepository extends JpaRepository<Outlet,Integer> {
    @Query(value = """
    SELECT
        o.id AS id,
        r.name AS name,
        r.image AS image,
        o.outlet_status AS status,
        ol.latitude AS latitude,
        ol.longitude AS longitude
    FROM outlet o
    INNER JOIN restaurant r
        ON r.id = o.restaurant_id
    INNER JOIN outlet_location ol
        ON ol.outlet_id = o.id
    WHERE o.outlet_status = :status
    ORDER BY
        ST_SetSRID(
            ST_MakePoint(ol.longitude, ol.latitude),
            4326
        )
        <->
        ST_SetSRID(
            ST_MakePoint(:longitude, :latitude),
            4326
        )
    LIMIT :limit
    """,
            nativeQuery = true)
    List<OutletR> findNearestOutlets(
            @Param("latitude") double lat,
            @Param("longitude") double lon,
            @Param("status") OutletStatus status,
            @Param("limit") int limit
    );

    @Query("""
                SELECT
                o.id,
                r.name ,
                r.image ,
                o.outletStatus AS status,
                o.latitude,
                o.longitude ,
                o.managerMobNo
                FROM Outlet o
                JOIN o.restaurant r
                WHERE o.merchantMobNo = :merchantMobNo
    """)

    List<OutletDetailed> findAllByMerchantMobNo(
            String merchantMobNo
    );

    @EntityGraph(attributePaths = {"merchant","outletInvitation"})
    Optional<Outlet> findByIdAndMerchantMobNo(Integer id ,String managerMobNo);


    @Query("""
                SELECT new com.twilight.dataTransferObjects.OutletR(
                o.id ,
                r.name,
                r.image ,
                o.outletStatus ,
                o.latitude,
                o.longitude
                )
                FROM Outlet o
                JOIN o.restaurant r
                WHERE o.id = :outletId
    """)
    Optional<OutletR> findByOutletId(
            @Param("outletId") Integer outletId
    );

    @EntityGraph(attributePaths = {"location"})
    @NonNull
    Optional<Outlet> findById(@NonNull Integer outletId);

    List<Outlet> findAllByRestaurantId(Integer restaurantId);

}
