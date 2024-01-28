package com.gonion.apigw.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthorizationFilter implements GlobalFilter, Ordered {
  private final ApiKeyAuthorizationChecker apiKeyAuthorizationChecker;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    Route attribute =  exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    assert attribute != null;
    var applicationName = attribute.getId();

    var apiKey = exchange.getRequest().getHeaders().get("ApiKey");

    if (applicationName == null ||
            apiKey == null ||
            apiKey.isEmpty() ||
            !apiKeyAuthorizationChecker.isAuthorized(apiKey.get(0), applicationName)
    ) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "You are not authorized"
      );
    }

    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
