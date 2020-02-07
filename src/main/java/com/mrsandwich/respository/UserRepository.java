package com.mrsandwich.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsandwich.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByPhoneNumberAndPassword(Long phoneNumber, String password);

}
