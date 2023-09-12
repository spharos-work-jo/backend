package com.workjo.pointapp.partner.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="receipt_partner_category")
public class ReceiptPartnerCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer parentId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partner_id")
	private SsgPartner ssgPartner;

}
