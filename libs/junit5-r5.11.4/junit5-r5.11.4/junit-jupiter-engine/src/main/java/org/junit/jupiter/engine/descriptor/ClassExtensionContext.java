/*
 * Copyright 2015-2024 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.descriptor;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExecutableInvoker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstances;
import org.junit.jupiter.engine.config.JupiterConfiguration;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.support.hierarchical.Node;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

/**
 * @since 5.0
 */
final class ClassExtensionContext extends AbstractExtensionContext<ClassBasedTestDescriptor> {

	private final Lifecycle lifecycle;

	private final ThrowableCollector throwableCollector;

	private TestInstances testInstances;

	/**
	 * Create a new {@code ClassExtensionContext} with {@link Lifecycle#PER_METHOD}.
	 *
	 * @see #ClassExtensionContext(ExtensionContext, EngineExecutionListener, ClassBasedTestDescriptor,
	 * Lifecycle, JupiterConfiguration, ThrowableCollector, Function)
	 */
	ClassExtensionContext(ExtensionContext parent, EngineExecutionListener engineExecutionListener,
			ClassBasedTestDescriptor testDescriptor, JupiterConfiguration configuration,
			ThrowableCollector throwableCollector,
			Function<ExtensionContext, ExecutableInvoker> executableInvokerFactory) {

		this(parent, engineExecutionListener, testDescriptor, Lifecycle.PER_METHOD, configuration, throwableCollector,
			executableInvokerFactory);
	}

	ClassExtensionContext(ExtensionContext parent, EngineExecutionListener engineExecutionListener,
			ClassBasedTestDescriptor testDescriptor, Lifecycle lifecycle, JupiterConfiguration configuration,
			ThrowableCollector throwableCollector,
			Function<ExtensionContext, ExecutableInvoker> executableInvokerFactory) {

		super(parent, engineExecutionListener, testDescriptor, configuration, executableInvokerFactory);

		this.lifecycle = lifecycle;
		this.throwableCollector = throwableCollector;
	}

	@Override
	public Optional<AnnotatedElement> getElement() {
		return Optional.of(getTestDescriptor().getTestClass());
	}

	@Override
	public Optional<Class<?>> getTestClass() {
		return Optional.of(getTestDescriptor().getTestClass());
	}

	@Override
	public Optional<Lifecycle> getTestInstanceLifecycle() {
		return Optional.of(this.lifecycle);
	}

	@Override
	public Optional<Object> getTestInstance() {
		return getTestInstances().map(TestInstances::getInnermostInstance);
	}

	@Override
	public Optional<TestInstances> getTestInstances() {
		return Optional.ofNullable(testInstances);
	}

	void setTestInstances(TestInstances testInstances) {
		this.testInstances = testInstances;
	}

	@Override
	public Optional<Method> getTestMethod() {
		return Optional.empty();
	}

	@Override
	public Optional<Throwable> getExecutionException() {
		return Optional.ofNullable(this.throwableCollector.getThrowable());
	}

	@Override
	protected Node.ExecutionMode getPlatformExecutionMode() {
		return getTestDescriptor().getExecutionMode();
	}

}
