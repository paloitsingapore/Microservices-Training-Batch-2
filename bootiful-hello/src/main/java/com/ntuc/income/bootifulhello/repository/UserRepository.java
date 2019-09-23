package com.ntuc.income.bootifulhello.repository;

import com.ntuc.income.bootifulhello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
