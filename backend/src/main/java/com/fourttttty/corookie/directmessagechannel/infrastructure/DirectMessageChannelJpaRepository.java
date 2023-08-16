package com.fourttttty.corookie.directmessagechannel.infrastructure;

import com.fourttttty.corookie.directmessagechannel.domain.DirectMessageChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectMessageChannelJpaRepository extends JpaRepository<DirectMessageChannel, Long> {
    Optional<DirectMessageChannel> findByProjectIdAndMember1IdAndMember2Id(Long member1Id, Long member2Id, Long projectId);
    List<DirectMessageChannel> findByProjectIdAndMember1IdOrMember2Id(Long member1Id, Long member2Id, Long projectId);
}
