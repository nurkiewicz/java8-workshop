package com.nurkiewicz.rxjava.stock;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {

	private final String id;
	private final String symbol;
	private final BigDecimal price;
	private final long amount;
	private final Instant timestamp;

	public Transaction(String id, String symbol, BigDecimal price, long amount, Instant timestamp) {
		this.id = id;
		this.symbol = symbol;
		this.price = price;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getAmount() {
		return amount;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public String getId() {
		return id;
	}
}
