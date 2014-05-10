package com.nurkiewicz.java8.atomic;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BigDecimalAccumulator {

	private final BigDecimal initialValue;
	private final BinaryOperator<BigDecimal> accFun;
	private final List<AtomicReference<BigDecimal>> accumulators;

	public BigDecimalAccumulator(BigDecimal initialValue, BinaryOperator<BigDecimal> accFun) {
		this(initialValue, accFun, availCpuMin4());
	}

	public BigDecimalAccumulator(BigDecimal initialValue, BinaryOperator<BigDecimal> accFun, int concurrency) {
		this.initialValue = initialValue;
		this.accFun = accFun;
		this.accumulators = IntStream
				.range(0, concurrency)
				.mapToObj(i -> new AtomicReference<>(initialValue))
				.collect(toList());
	}

	private static int availCpuMin4() {
		return Math.max(Runtime.getRuntime().availableProcessors(), 4);
	}

	public BigDecimal get() {
		return accumulators.stream()
				.map(AtomicReference::get)
				.reduce(initialValue, accFun);
	}

	public void accumulate(BigDecimal value) {
		final int i = (int) (Thread.currentThread().getId() % accumulators.size());
		accumulators.get(i).accumulateAndGet(value, accFun);
	}

	public void reset() {
		accumulators.forEach(a -> a.set(initialValue));
	}

	public BigDecimal getAndReset() {
		return accumulators.stream()
				.map(ref -> ref.getAndSet(initialValue))
				.reduce(initialValue, accFun);
	}

}
