package com.ecommerce_parent.authentication_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenClaims {
    private String email;
    private Date expiration;
}
