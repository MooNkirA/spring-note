/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction.support;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

/**
 * Template class that simplifies programmatic transaction demarcation and
 * transaction exception handling.
 *
 * <p>The central method is {@link #execute}, supporting transactional code that
 * implements the {@link TransactionCallback} interface. This template handles
 * the transaction lifecycle and possible exceptions such that neither the
 * TransactionCallback implementation nor the calling code needs to explicitly
 * handle transactions.
 *
 * <p>Typical usage: Allows for writing low-level data access objects that use
 * resources such as JDBC DataSources but are not transaction-aware themselves.
 * Instead, they can implicitly participate in transactions handled by higher-level
 * application services utilizing this class, making calls to the low-level
 * services via an inner-class callback object.
 *
 * <p>Can be used within a service implementation via direct instantiation with
 * a transaction manager reference, or get prepared in an application context
 * and passed to services as bean reference. Note: The transaction manager should
 * always be configured as bean in the application context: in the first case given
 * to the service directly, in the second case given to the prepared template.
 *
 * <p>Supports setting the propagation behavior and the isolation level by name,
 * for convenient configuration in context definitions.
 *
 * @author Juergen Hoeller
 * @since 17.03.2003
 * @see #execute
 * @see #setTransactionManager
 * @see org.springframework.transaction.PlatformTransactionManager
 */
/* 此类是用于编程式事务的模板对象 */
@SuppressWarnings("serial")
public class TransactionTemplate extends DefaultTransactionDefinition
		implements TransactionOperations, InitializingBean {

	/** Logger available to subclasses. */
	/* 定义日志组件 */
	protected final Log logger = LogFactory.getLog(getClass());

	/* 定义事务管理器对象 */
	@Nullable
	private PlatformTransactionManager transactionManager;


	/**
	 * Construct a new TransactionTemplate for bean usage.
	 * <p>Note: The PlatformTransactionManager needs to be set before
	 * any {@code execute} calls.
	 * @see #setTransactionManager
	 */
	/* 默认构造函数 */
	public TransactionTemplate() {
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager.
	 * @param transactionManager the transaction management strategy to be used
	 */
	/* 通过事务管理器构建事务模板对象 */
	public TransactionTemplate(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager,
	 * taking its default settings from the given transaction definition.
	 * @param transactionManager the transaction management strategy to be used
	 * @param transactionDefinition the transaction definition to copy the
	 * default settings from. Local properties can still be set to change values.
	 */
	/* 通过事务管理器和事务定义信息构建模板对象 */
	public TransactionTemplate(PlatformTransactionManager transactionManager, TransactionDefinition transactionDefinition) {
		super(transactionDefinition);
		this.transactionManager = transactionManager;
	}


	/**
	 * Set the transaction management strategy to be used.
	 */
	/* 当使用默认构造函数构建事务模板对象时，可以通过此方法注入事务管理器 */
	public void setTransactionManager(@Nullable PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Return the transaction management strategy to be used.
	 */
	/* 获取使用的事务管理器对象 */
	@Nullable
	public PlatformTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	/* 重写InitializingBean接口中的方法，用于初始化时验证是否有事务管理器 */
	@Override
	public void afterPropertiesSet() {
		if (this.transactionManager == null) {
			throw new IllegalArgumentException("Property 'transactionManager' is required");
		}
	}

	/* 编程事务控制的核心方法,重写的是TransactionOperations接口中的方法 */
	@Override
	@Nullable
	public <T> T execute(TransactionCallback<T> action) throws TransactionException {
		Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");

		/*
		 * 判断当前事务管理器是否为CallbackPreferringPlatformTransactionManager类型，
		 * 如果是的话，直接使用该接口实现类中的execute方法执行。
		 * 而无需继续让PlatformTransactionManager的实现类控制事务，
		 * 当前坐标环境下它只有一个实现类：WebSphereUowTransactionManager
		 */
		if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
			return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
		}
		else {
			// 需要借助PlatformTransactionManager的实现类控制事务
			TransactionStatus status = this.transactionManager.getTransaction(this);
			T result;
			try {
				/*
				 * 执行TransactionCallback中的doInTransaction方法，此处又是策略模式。
				 * spring只提供了一个接口（还有一个抽象实现类），而具体需要事务支持的业务代码由使用者提供。
				 */
				result = action.doInTransaction(status);
			}
			catch (RuntimeException | Error ex) {
				// Transactional code threw application exception -> rollback
				// 当doInTransaction执行有异常时事务回滚
				rollbackOnException(status, ex);
				throw ex;
			}
			catch (Throwable ex) {
				// Transactional code threw unexpected exception -> rollback
				// 当doInTransaction执行有无法预知的异常时，事务回滚。
				rollbackOnException(status, ex);
				throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
			}
			// 没有异常，则事务提交
			this.transactionManager.commit(status);
			// 返回执行结果（有可能是结果集，也有可能是影响数据库记录的行数）
			return result;
		}
	}

	/**
	 * Perform a rollback, handling rollback exceptions properly.
	 * @param status object representing the transaction
	 * @param ex the thrown application exception or error
	 * @throws TransactionException in case of a rollback error
	 */
	/* 回滚事务的方法 */
	private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
		Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");

		logger.debug("Initiating transaction rollback on application exception", ex);
		try {
			// 执行事务管理器中提供的回滚方法
			this.transactionManager.rollback(status);
		}
		catch (TransactionSystemException ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			ex2.initApplicationException(ex);
			throw ex2;
		}
		catch (RuntimeException | Error ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			throw ex2;
		}
	}

	/* 重写equals方法 */
	@Override
	public boolean equals(Object other) {
		return (this == other || (super.equals(other) && (!(other instanceof TransactionTemplate) ||
				getTransactionManager() == ((TransactionTemplate) other).getTransactionManager())));
	}

}
