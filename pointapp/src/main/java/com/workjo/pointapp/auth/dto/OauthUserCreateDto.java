package com.workjo.pointapp.auth.dto;


import com.workjo.pointapp.auth.domain.OauthProviderType;
import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OauthUserCreateDto {

	private String oauthId;
	private OauthProviderType provider;
	private UUID uuid;

}
