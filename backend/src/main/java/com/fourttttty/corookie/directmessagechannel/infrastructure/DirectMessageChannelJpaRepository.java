package com.fourttttty.corookie.directmessagechannel.infrastructure;

import com.fourttttty.corookie.directmessagechannel.domain.DirectMessageChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DirectMessageChannelJpaRepository extends JpaRepository<DirectMessageChannel, Long> {
    Optional<DirectMessageChannel> findByProjectIdAndMember1IdAndMember2Id(Long member1Id, Long member2Id, Long projectId);

    @Query("select dmc from DirectMessageChannel dmc where dmc.project.id = :projectId and (dmc.member1.id = :member1Id or dmc.member2.id = :member2Id) ")
    List<DirectMessageChannel> findByProjectIdAndMember1IdOrMember2Id(Long member1Id, Long member2Id, Long projectId);
}
