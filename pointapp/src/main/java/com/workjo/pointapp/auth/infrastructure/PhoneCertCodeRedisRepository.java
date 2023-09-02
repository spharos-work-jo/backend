package com.workjo.pointapp.auth.infrastructure;


import com.workjo.pointapp.auth.domain.PhoneCertCode;
import org.springframework.data.repository.CrudRepository;


public interface PhoneCertCodeRedisRepository extends CrudRepository<PhoneCertCode, String> {

}
