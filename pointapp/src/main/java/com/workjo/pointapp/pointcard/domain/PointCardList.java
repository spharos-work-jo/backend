package com.workjo.pointapp.pointcard.domain;


import com.workjo.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "point_card_list")
public class PointCardList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "point_card_id")
	private PointCard pointCard;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
