package com.ucm.reportsService.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ucm.reportsService.entity.model.Customer;

public interface ReportsRepo extends JpaRepository<Customer, Integer> {

	Customer findByUserId(String userId);
	
	List<Customer> findByManagerId(int managerId);

}
