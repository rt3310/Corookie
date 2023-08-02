package com.fourttttty.corookie.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth2 {

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(nullable = false)
    private String account;

    private Oauth2(AuthProvider authProvider, String account) {
        this.authProvider = authProvider;
        this.account = account;
    }

    public static Oauth2 of(AuthProvider authProvider, String account) {
        return new Oauth2(authProvider, account);
    }
}
