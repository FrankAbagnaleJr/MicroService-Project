package com.kyrie.order.clients;


import com.kyrie.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface OrderClient {
    @GetMapping("/user/{id}")
    User selectById(@PathVariable("id") Long id);
}
