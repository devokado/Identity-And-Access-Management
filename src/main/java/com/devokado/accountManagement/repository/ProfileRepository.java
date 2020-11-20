package com.devokado.accountManagement.repository;

import com.devokado.accountManagement.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByMobileAndCode(String mobile, String code);
}