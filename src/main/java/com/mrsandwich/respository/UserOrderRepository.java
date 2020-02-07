package com.mrsandwich.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsandwich.entity.User;
import com.mrsandwich.entity.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long>{

	List<UserOrder> findTop3ByUserId(User userId);
	
	List<UserOrder> findByUserId(User user);
}
