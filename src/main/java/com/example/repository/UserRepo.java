package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(Integer userId);

	@Query(value = "SELECT * from users WHERE UPPER(users.email) LIKE UPPER(CONCAT('%', ?1, '%')) " +
			"ORDER BY users.user_id LIMIT ?2"
			, nativeQuery = true)
	List<User> findUsers_EmailContains(String characters, Long limit);

}
