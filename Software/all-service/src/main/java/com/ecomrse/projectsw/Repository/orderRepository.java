package com.ecomrse.projectsw.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomrse.projectsw.Models.order;

@Repository
public interface orderRepository extends JpaRepository<order, Long>{

}
