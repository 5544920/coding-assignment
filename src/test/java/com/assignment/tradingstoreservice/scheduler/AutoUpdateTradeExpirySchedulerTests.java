package com.assignment.tradingstoreservice.scheduler;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.tradingstoreservice.dao.TradingStoreRepository;
import com.assignment.tradingstoreservice.model.TradeBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoUpdateTradeExpirySchedulerTests {
	@Autowired
	AutoUpdateTradeExpiryScheduler scheduler;

	@MockBean
	private TradingStoreRepository repo;

	@Test
	public void testAutoUpdateExpiryFlag() {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(3);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/07/2021");
		tradeBean2.setExpiredFlag("N");
		List<TradeBean> beans = new ArrayList<>();
		beans.add(tradeBean2);
		when(repo.findAll()).thenReturn(beans);
		scheduler.autoUpdateExpiryFlag();

	}

	@Test
	public void testAutoUpdateExpiryFlag2() {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(3);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/03/2021");
		tradeBean2.setExpiredFlag("N");
		List<TradeBean> beans = new ArrayList<>();
		beans.add(tradeBean2);
		when(repo.findAll()).thenReturn(beans);
		scheduler.autoUpdateExpiryFlag();
	}

	@Test
	public void testAutoUpdateExpiryFlag3() {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(3);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("");
		tradeBean2.setExpiredFlag("N");
		List<TradeBean> beans = new ArrayList<>();
		beans.add(tradeBean2);
		when(repo.findAll()).thenReturn(beans);
		scheduler.autoUpdateExpiryFlag();
	}

}
