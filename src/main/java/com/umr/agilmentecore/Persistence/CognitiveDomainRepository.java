package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.CognitiveDomain;

@Repository
public interface CognitiveDomainRepository extends JpaRepository<CognitiveDomain, Integer>{
	
}
