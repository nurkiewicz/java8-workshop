package com.nurkiewicz.java8.atomic;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class BigDecimalAccumulator {

	private final BiFunction<BigDecimal, BigDecimal, BigDecimal> accFun;
	private final BigDecimal[] accumulators;

	public BigDecimalAccumulator(BigDecimal initialValue, BiFunction<BigDecimal, BigDecimal, BigDecimal> accFun) {
		this(initialValue, accFun, availCpuMin4());
	}

	public BigDecimalAccumulator(BigDecimal initialValue, BiFunction<BigDecimal, BigDecimal, BigDecimal> accFun, int concurrency) {
		this.accFun = accFun;
		this.accumulators = new BigDecimal[concurrency];
		for (int i = 0; i < accumulators.length; i++) {
			accumulators[i] = initialValue;
		}
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
