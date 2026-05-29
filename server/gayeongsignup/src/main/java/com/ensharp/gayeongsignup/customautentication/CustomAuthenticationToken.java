package com.ensharp.gayeongsignup.customautentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Object principal; //
    private Object credentials; //자격증명

    //인증 전 생성자
    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials=credentials;
        setAuthenticated(false);
    }
    //인증 후 생성자
    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
        super(authorities);
        this.principal = principal;
        this.credentials=credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
        Assert.isTrue(!isAuthenticated, "토큰을 지정할 수 없다?");
        super.setAuthenticated(false);
    }
    @Override
    public void eraseCredentials(){
        super.eraseCredentials();
        this.credentials =null;
    }
}
