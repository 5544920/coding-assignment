package com.assignment.tradingstoreservice.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.tradingstoreservice.exception.InvalidTradeException;
import com.assignment.tradingstoreservice.model.TradeBean;
import com.assignment.tradingstoreservice.service.TradingStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradingStoreControllerTests {

	@Autowired
	private TradingStoreController controller;

	@MockBean
	private TradingStoreService service;

	@Test
	public void testSaveTradeRecordValidInput() throws InvalidTradeException, ParseException {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(2);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/07/2021");
		tradeBean2.setExpiredFlag("N");

		when(service.saveOrUpdateTradeRecord(tradeBean2)).thenReturn(true);
		ResponseEntity<String> result = controller.saveTradeRecord(tradeBean2);
		assertNotNull(result);

	}

	@Test(expected = InvalidTradeException.class)
	public void testSaveTradeRecordInValidInput() throws InvalidTradeException, ParseException {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(2);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/03/2021");
		tradeBean2.setExpiredFlag("N");

		when(service.saveOrUpdateTradeRecord(tradeBean2)).thenReturn(false);
		controller.saveTradeRecord(tradeBean2);
		// assertNotNull(result);

	}

	@Test(expected = InvalidTradeException.class)
	public void testSaveTradeRecordInValidVersion() throws InvalidTradeException, ParseException {
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(2);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/08/2021");
		tradeBean2.setExpiredFlag("N");

		when(service.saveOrUpdateTradeRecord(tradeBean2)).thenReturn(false);
		controller.saveTradeRecord(tradeBean2);
		// assertNotNull(result);

	}

	@Test
	public void testFindAllTrades() {
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
		when(service.getAllTradeRecords()).thenReturn(beans);
		controller.findAllTrades();
	}

}
