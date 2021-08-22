package com.wanggl.h2sql.repository;

import com.wanggl.h2sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
