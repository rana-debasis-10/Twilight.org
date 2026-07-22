package com.twilight.repositories;

import com.twilight.objects.Merchant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<@NonNull Merchant, @NonNull String> {
    @Override
    @NonNull
    @EntityGraph(attributePaths = {"restaurant"})
    Optional<@NonNull Merchant> findById(@NonNull String mobNo);
}
