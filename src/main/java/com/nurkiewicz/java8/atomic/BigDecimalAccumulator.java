package com.nurkiewicz.java8.atomic;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

public class BigDecimalAccumulator {

	private final BinaryOperator<BigDecimal> accFun;

	public BigDecimalAccumulator(BigDecimal initialValue, BinaryOperator<BigDecimal> accFun) {
		this(initialValue, accFun, availCpuMin4());
	}

	public BigDecimalAccumulator(BigDecimal initialValue, BinaryOperator<BigDecimal> accFun, int concurrency) {
		this.accFun = accFun;
	}

	private static int availCpuMin4() {
		return Math.max(Runtime.getRuntime().availableProcessors(), 4);
	}

	public BigDecimal get() {
		throw new UnsupportedOperationException("get()");
	}

	public void accumulate(BigDecimal value) {
		throw new UnsupportedOperationException("accumulate()");
	}

	public void reset() {
		throw new UnsupportedOperationException("reset()");
	}

	public BigDecimal getAndReset() {
		throw new UnsupportedOperationException("getAndReset()");
	}

}
