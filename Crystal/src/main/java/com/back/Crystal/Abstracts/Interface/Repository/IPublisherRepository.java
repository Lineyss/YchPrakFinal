package com.back.Crystal.Abstracts.Interface.Repository;

import com.back.Crystal.Model.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublisherRepository extends JpaRepository<Publisher, Long> {
}
