package com.workjo.pointapp.auth.dto;


import com.workjo.pointapp.auth.domain.OauthProviderType;
import lombok.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OauthUserLoginDto {

	private String oauthId;
	private OauthProviderType provider;

}
