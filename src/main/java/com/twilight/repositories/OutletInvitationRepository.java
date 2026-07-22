package com.twilight.repositories;

import com.twilight.objects.OutletInvitation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface OutletInvitationRepository extends JpaRepository <OutletInvitation,Integer> {
    List<OutletInvitation> findByMerchantMobNo(String mobNo, Pageable pageable);

    List<OutletInvitation> findAllByMerchantMobNo(String merchantMobNo);

    @EntityGraph(attributePaths = {
            "outlet",
            "outlet.manager"
    })    Optional<OutletInvitation> findByIdAndInviteeMobNo(Integer invitationId, String inviteeMobNo);

    List<OutletInvitation> findAllByInviteeMobNo(String mobNo);
}
