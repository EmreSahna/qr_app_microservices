package com.emresahna.notificationservice.client;

import com.emresahna.notificationservice.dto.UserNotificationInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserService {
    @GetMapping("/user/get-user-notification-info/{id}")
    UserNotificationInfo getUserNotificationInfoById(@PathVariable String id);
}
