package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "agent") //samo proveriti da li se ovo vozilo odnosi na naziv u bazi za Vozilo service
public interface AgentConnection {

    @GetMapping("/verify/{agent_id}")
    boolean verify(@PathVariable("agent_id") Long agent_id);

}
