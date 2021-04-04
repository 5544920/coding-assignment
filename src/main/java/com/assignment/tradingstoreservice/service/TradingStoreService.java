package com.assignment.tradingstoreservice.service;

import java.util.List;

import com.assignment.tradingstoreservice.model.TradeBean;

public interface TradingStoreService {

	public boolean saveOrUpdateTradeRecord(TradeBean tradeBean);

	public List<TradeBean> getAllTradeRecords();

	public void autoUpdateExpiryFlag();

}
