package com.godziatkowski.task;

import com.godziatkowski.service.IChannelService;
import java.util.Set;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChannelCleaningTask {

    private final IChannelService channelService;

    public ChannelCleaningTask(IChannelService channelService) {
        this.channelService = channelService;
    }

    @Scheduled(fixedRate = 1000 * 2 * 60)
    private void removeInactiveUsers() {
        Set<Long> emptyChannelsIds = channelService.findEmptyChannels();
        emptyChannelsIds.stream().forEach((emptyChannelsId) -> {
            channelService.closeChannel(emptyChannelsId);
        });
    }

}
