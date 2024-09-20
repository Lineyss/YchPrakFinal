package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
