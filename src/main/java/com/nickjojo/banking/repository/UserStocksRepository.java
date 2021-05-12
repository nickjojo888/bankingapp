package com.nickjojo.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nickjojo.banking.entity.UserStockTicker;

@Repository
public interface UserStocksRepository extends JpaRepository<UserStockTicker, Long> {

	@Query(value = "SELECT * FROM user_stocks WHERE user_accountNumber = ?1", nativeQuery = true)
	List<UserStockTicker> findAllByUser_AccountNumber(String accountNumber);

	@Modifying
	@Query(value = "DELETE FROM user_stocks s WHERE s.ticker=:ticker and s.user_accountNumber=:accountNumber", nativeQuery = true)
	void remove(@Param("ticker") String ticker, @Param("accountNumber") String user_accountNumber);

}
