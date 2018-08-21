package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.model.OrderDetails;
import com.capgemini.repository.RefundMoneyRepository;

@Service
public class RefundMoneyImpl implements RefundMoney{
	@Autowired
	RefundMoneyRepository repo;
	@Override
	public String refundDisplay(int id) {
		OrderDetails o= repo.refundDisplay(id);	
		//getting index of  returned
		String status=o.getStatus().substring(0,8);		
		 if(o.getStatus().length()>9)
		{
			 //returning delivery status for 1 or more products
			return "Refund Successful for partial order with "+o.getStatus();
		}
		 //returning delivery status for whole order
		 else if(status.equalsIgnoreCase("Returned"))	{
				
			return "Refund Successful for whole order";
				
		}	
		
		else if(o.getStatus().equalsIgnoreCase("Delivered")) {
			return "Order Delivered Successfully, But NotReturned yet";
		}
			
		else return "Sorry,Product is not Delivered. You are not Eligible for Refund";

	}

}
