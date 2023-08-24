package com.workjo.pointapp.partner.domain;


import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum SSGReceiptPartner implements BaseEnum<String, String> {

	EMART("E00000", "이마트"),
	DEPARTMENT("D00000", "신세계 백화점"),
	EMART_EVERYDAY("E00001", "이마트 에브리데이");

	private final String code;
	private final String value;

	@jakarta.persistence.Converter(autoApply = true)
	static class Converter extends AbstractBaseEnumConverter<SSGReceiptPartner, String, String> {

		public Converter() {
			super(SSGReceiptPartner.class);
		}

	}
}
