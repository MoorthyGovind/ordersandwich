package com.mrsandwich.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsandwich.entity.UserOrder;
import com.mrsandwich.entity.UserOrderItem;

public interface UserOrderItemRepository extends JpaRepository<UserOrderItem, Integer>{

	List<UserOrderItem> findByUserOrderId(UserOrder userOrder);
}
