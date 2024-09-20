package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISoftwareRepository extends JpaRepository<Software, Long> {
}
