package com.twilight.repositories;

import com.twilight.objects.Food;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<@NonNull Food,@NonNull Integer> {
    @EntityGraph(attributePaths = {"outlet"})
    List<Food>findByOutletId(Integer outletId);

    List<Food> findAllByIdInAndOutletIdAndAvailableTrue(
            List<Integer> foodIds,
            Integer outletId
    );

    Optional<Food> findByIdAndOutletId(
            Integer foodId,
            Integer outletId);


}



