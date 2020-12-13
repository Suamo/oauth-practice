package com.example.social.usertype;

import com.example.social.CryptoAuthenticatedPrincipal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Getter
@Setter
public class GithubConnectUser implements OAuth2User, CryptoAuthenticatedPrincipal {

    private String id;
    private String name;
    private String login;
    private String email;
    private List<GrantedAuthority> authorities = createAuthorityList("ROLE_USER");
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<String, Object>() {{
                put("id", getId());
                put("name", getName());
                put("login", getLogin());
                put("email", getEmail());
            }};
        }
        return attributes;
    }

    @Override
    public String getUserName() {
        return getName();
    }

    public String getName() {
        return login;
    }
}
