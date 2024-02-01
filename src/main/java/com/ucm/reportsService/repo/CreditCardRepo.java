package com.ucm.reportsService.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucm.reportsService.entity.model.CreditCardTransaction;

public interface CreditCardRepo extends JpaRepository<CreditCardTransaction, Integer> {

	@Query(value="select * from credit_card_transaction where customer_id = ?1 and date > ?2 and date < ?3 order by id", nativeQuery = true)
	List<CreditCardTransaction> credittransactionlist(int customerId, Date fromdate, Date todate);
}
