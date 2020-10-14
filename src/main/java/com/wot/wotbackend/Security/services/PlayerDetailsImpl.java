package com.wot.wotbackend.Security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wot.wotbackend.characterModel.Character;
import com.wot.wotbackend.documents.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Double latitude;

    private Double longitude;

    private Character playerCharacter;

    private Collection<? extends GrantedAuthority> authorities;



    public static PlayerDetailsImpl build(Player player) {
        List<GrantedAuthority> authorities = player.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new PlayerDetailsImpl(
                player.getId(),
                player.getUsername(),
                player.getEmail(),
                player.getPassword(),
                player.getLatitude(),
                player.getLongitude(),
                player.getPlayerCharacter(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerDetailsImpl user = (PlayerDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
