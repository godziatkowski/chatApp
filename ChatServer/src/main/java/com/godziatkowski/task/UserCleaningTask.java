package com.godziatkowski.task;

import com.godziatkowski.service.IChannelService;
import com.godziatkowski.service.IUserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserCleaningTask {

    private final IUserService userService;
    private final IChannelService channelService;

    @Autowired
    public UserCleaningTask(IUserService userService, IChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @Scheduled(fixedRate = 1000 * 5 * 60)
    private void removeInactiveUsers() {
        Set<Long> inactiveUsers = userService.findInactiveUsers();
        inactiveUsers.stream().forEach((inactiveUserId) -> {
            channelService.removeUserFromChannels(inactiveUserId);
            userService.logout(inactiveUserId);
        });
    }

}
