package com.mytaxi.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytaxi.domainobject.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);
}