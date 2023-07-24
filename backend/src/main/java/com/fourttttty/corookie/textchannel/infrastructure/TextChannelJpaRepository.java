package com.fourttttty.corookie.textchannel.infrastructure;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextChannelJpaRepository extends JpaRepository<TextChannel, Long> {
}
