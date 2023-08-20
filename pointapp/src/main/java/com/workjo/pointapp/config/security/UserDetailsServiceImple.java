package com.workjo.pointapp.config.security;


import com.workjo.pointapp.admin.domain.Admin;
import com.workjo.pointapp.admin.infrastructure.AdminRepository;
import com.workjo.pointapp.auth.domain.Member;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImple implements UserDetailsService {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;


	@Override
	public Member loadUserByUsername(String loginId) throws UsernameNotFoundException {
		Member member;
		Optional<User> user = userRepository.findByLoginId(loginId);
		Optional<Admin> admin = adminRepository.findByLoginId(loginId);

		if (user.isEmpty() && admin.isEmpty()) throw new UsernameNotFoundException("로그인 실패");
		else if (user.isPresent()) {
			member = user.orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_ERROR));
		} else {
			member = admin.orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_ERROR));
		}
		return member;
	}

}