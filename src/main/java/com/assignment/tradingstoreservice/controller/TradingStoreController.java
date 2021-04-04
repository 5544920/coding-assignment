package com.assignment.tradingstoreservice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.tradingstoreservice.exception.InvalidTradeException;
import com.assignment.tradingstoreservice.model.TradeBean;
import com.assignment.tradingstoreservice.service.TradingStoreService;
import com.assignment.tradingstoreservice.utility.TradingStoreValidationUtil;

@RestController
public class TradingStoreController {
	@Autowired
	private TradingStoreService tradingStoreService;

	@PostMapping("/trade")
	public ResponseEntity<String> saveTradeRecord(@RequestBody TradeBean tradeBean)
			throws InvalidTradeException, ParseException {
		if (TradingStoreValidationUtil.isValidMaturityDate(tradeBean.getMaturityDate())) {
			boolean saveFlag = tradingStoreService.saveOrUpdateTradeRecord(tradeBean);
			if (!saveFlag)
				throw new InvalidTradeException("Trade version is not valid for trade Id :" + tradeBean.getTradeId());
		} else
			throw new InvalidTradeException("Trade maturity date is not valid for trade Id :" + tradeBean.getTradeId());

		return new ResponseEntity<>("Record inserted", HttpStatus.CREATED);
	}

	@GetMapping("/trade")
	public ResponseEntity<List<TradeBean>> findAllTrades() {
		List<TradeBean> records = tradingStoreService.getAllTradeRecords();

		return new ResponseEntity<List<TradeBean>>(records, HttpStatus.OK);

	}

}
