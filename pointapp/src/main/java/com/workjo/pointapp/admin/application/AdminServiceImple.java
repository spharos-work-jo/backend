package com.workjo.pointapp.admin.application;


import com.workjo.pointapp.admin.infrastructure.AdminRepository;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService {

	private final AdminRepository adminRepository;


	@Override
	public void checkCanUseLoginId(String loginId) {
		if (adminRepository.existsByLoginId(loginId)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
	}

}
