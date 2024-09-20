package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHostRepository extends JpaRepository<Host, Long> {
}
