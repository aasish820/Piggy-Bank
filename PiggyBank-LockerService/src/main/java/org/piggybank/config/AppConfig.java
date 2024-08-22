package org.piggybank.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    @RequestScope
    public RestTemplate keycloakRestTemplate(HttpServletRequest inReq) {
      // retrieve the auth header from incoming request
      final String authHeader =
        inReq.getHeader(HttpHeaders.AUTHORIZATION);
      final RestTemplate restTemplate = new RestTemplate();
      // add a token if an incoming auth header exists, only
      if (authHeader != null && !authHeader.isEmpty()) {
        // since the header should be added to each outgoing request,
        // add an interceptor that handles this.
        restTemplate.getInterceptors().add(
          (outReq, bytes, clientHttpReqExec) -> {
            outReq.getHeaders().set(
              HttpHeaders.AUTHORIZATION, authHeader
            );
            return clientHttpReqExec.execute(outReq, bytes);
          });
      }
      return restTemplate;
    }
  }

