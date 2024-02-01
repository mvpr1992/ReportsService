package com.ucm.reportsService.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucm.reportsService.entity.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	@Query(value="select * from transaction where customer_id = ?1 and dateof_transaction > ?2 and dateof_transaction < ?3 order by transaction_id", nativeQuery = true)
	List<Transaction> findByCustomerIdList(int customerId, Date fromdate, Date todate);
}
