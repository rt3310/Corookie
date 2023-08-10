package com.fourttttty.corookie.videochannel.infrastructure;

import com.fourttttty.corookie.videochannel.domain.VideoChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoChannelJpaRepository extends JpaRepository<VideoChannel, Long> {
}
