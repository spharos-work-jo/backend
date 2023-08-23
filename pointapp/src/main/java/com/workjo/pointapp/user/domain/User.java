package com.workjo.pointapp.user.domain;


import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@ToString
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User extends BaseDateTime implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, columnDefinition = "BINARY(16)", name = "UUID")
	private UUID UUID;
	@Column(length = 45)
	private String loginId;
	@Column(length = 100)
	private String password; // todo: Hashing
	@Column(length = 45)
	private String name;
	@Column(length = 45)
	private String email;
	@Column(length = 20)
	private String phone;
	@Column(length = 100)
	private String address;
	@Column(columnDefinition = "tinyint default 1")
	private Boolean accountUse;
	@Column(length = 100)
	private String pointPassword; // todo: Hashing
	@Column(columnDefinition = "tinyint default 0")
	private Boolean agreement3rd;
	@Column(length = 255)
	private String barcodeImageUrl;


	public void updateAddressAndEmail(String address, String email) {
		this.address = address;
		this.email = email;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}


	@Override
	public String getUsername() {
		return UUID.toString();
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


	public void encodePassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

}
