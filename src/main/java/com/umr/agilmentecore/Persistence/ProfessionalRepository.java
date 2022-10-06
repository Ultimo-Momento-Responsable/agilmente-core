package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Professional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

	Professional findByUserNameAndPassword(String userName, String password);
	Professional findByToken(String token);
	Professional findByFirstNameAndLastName(String firstName, String lastName);
	Professional findByUserName(String userName);
}
