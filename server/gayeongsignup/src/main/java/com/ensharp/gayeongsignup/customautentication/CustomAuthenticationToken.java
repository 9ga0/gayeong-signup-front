package com.ensharp.gayeongsignup.customautentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

//사용자가 입력한 정보를 담고 서버를 돌아다님. 인증이 완료되면 권한 정보를 포함하여
// '이 사람은 인증된 사용자다'라는 인증서 역할을 수행함.
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    // 객체를 직렬화(세션 등에 저장)할 때 버전 관리용으로 사용되는 표준값

    private final Object principal; // 사용자 식별자(아이디, 이메일 등)를 저장
    private Object credentials; //비밀번호 같은 자격 증명 정보

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
