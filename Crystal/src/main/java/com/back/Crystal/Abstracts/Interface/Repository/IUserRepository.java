package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
