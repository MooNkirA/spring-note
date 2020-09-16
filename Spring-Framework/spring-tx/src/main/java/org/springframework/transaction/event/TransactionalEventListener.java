/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.transaction.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;

/**
 * An {@link EventListener} that is invoked according to a {@link TransactionPhase}.
 *
 * <p>If the event is not published within the boundaries of a managed transaction, the
 * event is discarded unless the {@link #fallbackExecution} flag is explicitly set. If a
 * transaction is running, the event is processed according to its {@code TransactionPhase}.
 *
 * <p>Adding {@link org.springframework.core.annotation.Order @Order} to your annotated
 * method allows you to prioritize that listener amongst other listeners running before
 * or after transaction completion.
 *
 * @author Stephane Nicoll
 * @author Sam Brannen
 * @since 4.2
 */
/* 事务的事件监听器，在事务提交和回滚前后可以做一些额外的功能 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventListener
public @interface TransactionalEventListener {

	/**
	 * Phase to bind the handling of an event to.
	 * <p>The default phase is {@link TransactionPhase#AFTER_COMMIT}.
	 * <p>If no transaction is in progress, the event is not processed at
	 * all unless {@link #fallbackExecution} has been enabled explicitly.
	 */
	/*
	 * 指定事务监听器的执行是在何时。取值有：
	 * 		TransactionPhase.BEFORE_COMMIT 事务提交之前
	 * 		TransactionPhase.AFTER_COMMIT 事务提交之后（默认值）
	 * 		TransactionPhase.AFTER_ROLLBACK 事务回滚之后
	 * 		TransactionPhase.AFTER_COMPLETION 事务执行完成之后
	 */
	TransactionPhase phase() default TransactionPhase.AFTER_COMMIT;

	/**
	 * Whether the event should be processed if no transaction is running.
	 */
	/* 若没有事务的时候，对应的event是否已经执行。默认值为false表示没事务就不执行了 */
	boolean fallbackExecution() default false;

	/**
	 * Alias for {@link #classes}.
	 */
	/* 指定事件类的字节码 */
	@AliasFor(annotation = EventListener.class, attribute = "classes")
	Class<?>[] value() default {};

	/**
	 * The event classes that this listener handles.
	 * <p>If this attribute is specified with a single value, the annotated
	 * method may optionally accept a single parameter. However, if this
	 * attribute is specified with multiple values, the annotated method
	 * must <em>not</em> declare any parameters.
	 */
	/* 它和value属性的作用是一样，指定事件类的字节码 */
	@AliasFor(annotation = EventListener.class, attribute = "classes")
	Class<?>[] classes() default {};

	/**
	 * Spring Expression Language (SpEL) attribute used for making the event
	 * handling conditional.
	 * <p>The default is {@code ""}, meaning the event is always handled.
	 * @see EventListener#condition
	 */
	/* 用于指定执行事件处理器的条件。取值是基于Spring的el表达式编写的 */
	String condition() default "";

}
