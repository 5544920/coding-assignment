package com.assignment.tradingstoreservice.serviceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.tradingstoreservice.dao.TradingStoreRepository;
import com.assignment.tradingstoreservice.model.TradeBean;
import com.assignment.tradingstoreservice.service.TradingStoreService;
import com.assignment.tradingstoreservice.utility.TradingStoreValidationUtil;

@Service
public class TradingStoreServiceImpl implements TradingStoreService {

	@Autowired
	private TradingStoreRepository repo;

	@Override
	public boolean saveOrUpdateTradeRecord(TradeBean tradeBean) {
		Optional<TradeBean> tradeBeanExisting = repo.findById(tradeBean.getTradeId());
		if (tradeBeanExisting.isPresent()) {
			//Version Validation
			if (tradeBean.getVersion() >= tradeBeanExisting.get().getVersion()) {
				repo.save(tradeBean);
				return true;
			} else
				return false;

		} else {
			repo.save(tradeBean);
			return true;
		}

	}

	@Override
	public List<TradeBean> getAllTradeRecords() {
		return repo.findAll();
	}

	@Override
	public void autoUpdateExpiryFlag() {
		List<TradeBean> beans = getAllTradeRecords();
		beans.stream().forEach(bean -> {
			String existingMaturityDate = bean.getMaturityDate();
			try {
				if (!TradingStoreValidationUtil.isValidMaturityDate(existingMaturityDate)) {
					bean.setExpiredFlag("Y");
					repo.save(bean);
				}
			} catch (ParseException e) {

			}

		});

	}

}
