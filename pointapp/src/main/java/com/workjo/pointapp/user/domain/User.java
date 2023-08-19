package com.workjo.pointapp.user.domain;


import com.workjo.pointapp.common.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@ToString
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User extends Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50, name = "UUID")
	private String UUID; // todo: UUID
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


	public void initUUID(String UUID) {
		if (this.UUID == null) {
			this.UUID = UUID;
		}
	}


	public void updateAddressAndEmail(String address, String email) {
		this.address = address;
		this.email = email;
	}

}
