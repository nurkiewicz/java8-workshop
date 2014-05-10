package com.nurkiewicz.java8;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.UNORDERED;

public class ImmutableSetCollector<T> implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {
	@Override
	public Supplier<ImmutableSet.Builder<T>> supplier() {
		return ImmutableSet.Builder::new;
	}

	@Override
	public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
		return (builder, t) -> builder.add(t);
	}

	@Override
	public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
		return (firstBuilder, secondBuilder) ->
				firstBuilder.addAll(secondBuilder.build());
	}

	@Override
	public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
		return ImmutableSet.Builder::build;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return ImmutableSet.of(UNORDERED);
	}
}
