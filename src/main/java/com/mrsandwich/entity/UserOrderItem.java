package com.mrsandwich.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userOrderItemId;
	@ManyToOne
	@JoinColumn(name = "user_order_id")
	private UserOrder userOrderId;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item itemId; 
	private Integer quantity;
}
