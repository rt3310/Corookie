package com.fourttttty.corookie.textchannel.infrastructure;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextChannelJpaRepository extends JpaRepository<TextChannel, Long> {
    List<TextChannel> findByProjectId(Long projectId);
}
