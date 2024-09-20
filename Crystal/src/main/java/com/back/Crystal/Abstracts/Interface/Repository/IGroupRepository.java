package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Long> {
}
