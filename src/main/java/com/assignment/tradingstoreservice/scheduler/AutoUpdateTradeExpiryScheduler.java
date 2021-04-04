package com.assignment.tradingstoreservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.tradingstoreservice.service.TradingStoreService;

@EnableScheduling
@Component
public class AutoUpdateTradeExpiryScheduler {

	@Autowired
	TradingStoreService tSerice;

	//Expired Flag Updation
	@Scheduled(cron = "${cron.expression}")
	public void autoUpdateExpiryFlag() {
		// System.out.println("Task started!" + new Date());
		tSerice.autoUpdateExpiryFlag();
		// System.out.println("Task ended!" + new Date());

	}

}
