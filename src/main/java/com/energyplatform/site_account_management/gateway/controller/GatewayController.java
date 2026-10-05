package com.energyplatform.site_account_management.gateway.controller;

import com.energyplatform.site_account_management.gateway.entity.Gateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gateways")
public class GatewayController {

    @GetMapping("/{siteId}/{gatewayId}")
    public Mono<Gateway> getGateway(@PathVariable String siteId, @PathVariable String gatewayId){

        return Mono.justOrEmpty(null);
    }

    @GetMapping("/{siteId}")
    public Flux<Gateway> getGateways(@PathVariable String siteId){
        return Flux.empty();
    }

    @PostMapping("/{siteId}")
    public Mono<Gateway> createGateway(@PathVariable String siteId, @RequestBody Gateway gateway){
        return Mono.justOrEmpty(null);
    }

    @PutMapping("/{siteId}/{gatewayId}")
    public Mono<Gateway> updateGateway(@PathVariable String siteId, @PathVariable String gatewayId, @RequestBody Gateway gateway){
        return Mono.justOrEmpty(null);
    }

    @DeleteMapping("/{siteId}/{gatewayId}")
    public Mono<Void> deleteGateway(@PathVariable String siteId, @PathVariable String gatewayId){
        return Mono.empty();
    }

}
