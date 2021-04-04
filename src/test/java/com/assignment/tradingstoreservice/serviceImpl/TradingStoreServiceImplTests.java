package com.assignment.tradingstoreservice.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.tradingstoreservice.dao.TradingStoreRepository;
import com.assignment.tradingstoreservice.model.TradeBean;
import com.assignment.tradingstoreservice.service.TradingStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradingStoreServiceImplTests {

	@Autowired
	private TradingStoreService service;

	@MockBean
	private TradingStoreRepository repo;

	private TradeBean tradeBean;

	@Before
	public void setData() {
		tradeBean = new TradeBean();
		tradeBean.setTradeId("T1");
		tradeBean.setVersion(2);
		tradeBean.setBookId("B1");
		tradeBean.setCounterPartyId("CP-1");
		tradeBean.setCreatedDate("12/02/2021");
		tradeBean.setMaturityDate("12/07/2021");
		tradeBean.setExpiredFlag("N");

	}

	@Test
	public void testGetAllTradeRecords() {
		List<TradeBean> beans = new ArrayList<>();
		beans.add(tradeBean);
		when(repo.findAll()).thenReturn(beans);
		List<TradeBean> response = service.getAllTradeRecords();
		assertNotNull(response);

	}

	@Test
	public void testSaveOrUpdateTradeRecordForExistingVersion() {
		tradeBean = new TradeBean();
		tradeBean.setTradeId("T1");
		tradeBean.setVersion(2);
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(2);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/07/2021");
		tradeBean2.setExpiredFlag("N");

		when(repo.findById(tradeBean.getTradeId())).thenReturn(Optional.of(tradeBean2));
		when(repo.save(tradeBean)).thenReturn(tradeBean);
		boolean result = service.saveOrUpdateTradeRecord(tradeBean);
		assertEquals(true, result);

	}

	@Test
	public void testSaveOrUpdateTradeRecordForExistingVersion1() {
		tradeBean = new TradeBean();
		tradeBean.setTradeId("T1");
		tradeBean.setVersion(2);
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(1);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/07/2021");
		tradeBean2.setExpiredFlag("N");

		when(repo.findById(tradeBean.getTradeId())).thenReturn(Optional.of(tradeBean2));
		when(repo.save(tradeBean)).thenReturn(tradeBean);
		boolean result = service.saveOrUpdateTradeRecord(tradeBean);
		assertEquals(true, result);

	}

	@Test
	public void testSaveOrUpdateTradeRecordForExistingVersion2() {
		tradeBean = new TradeBean();
		tradeBean.setTradeId("T1");
		tradeBean.setVersion(2);
		TradeBean tradeBean2 = new TradeBean();
		tradeBean2.setTradeId("T1");
		tradeBean2.setVersion(3);
		tradeBean2.setBookId("B1");
		tradeBean2.setCounterPartyId("CP-1");
		tradeBean2.setCreatedDate("12/02/2021");
		tradeBean2.setMaturityDate("12/07/2021");
		tradeBean2.setExpiredFlag("N");

		when(repo.findById(tradeBean.getTradeId())).thenReturn(Optional.of(tradeBean2));
		when(repo.save(tradeBean)).thenReturn(tradeBean);
		boolean result = service.saveOrUpdateTradeRecord(tradeBean);
		assertEquals(false, result);

	}

	@Test
	public void testSaveOrUpdateTradeRecordForNewVersion() {
		tradeBean = new TradeBean();
		tradeBean.setTradeId("T1");
		tradeBean.setVersion(2);
		when(repo.findById(tradeBean.getTradeId())).thenReturn(Optional.ofNullable(null));
		when(repo.save(tradeBean)).thenReturn(tradeBean);
		boolean result = service.saveOrUpdateTradeRecord(tradeBean);
		assertEquals(true, result);

	}

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
		service.autoUpdateExpiryFlag();
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
		service.autoUpdateExpiryFlag();
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
		service.autoUpdateExpiryFlag();
	}

}
