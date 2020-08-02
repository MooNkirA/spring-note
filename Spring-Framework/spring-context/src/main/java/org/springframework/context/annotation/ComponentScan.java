/*
 * Copyright 2002-2016 the original author or authors.
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

package org.springframework.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.type.filter.TypeFilter;

/**
 * Configures component scanning directives for use with @{@link Configuration} classes.
 * Provides support parallel with Spring XML's {@code <context:component-scan>} element.
 *
 * <p>Either {@link #basePackageClasses} or {@link #basePackages} (or its alias
 * {@link #value}) may be specified to define specific packages to scan. If specific
 * packages are not defined, scanning will occur from the package of the
 * class that declares this annotation.
 *
 * <p>Note that the {@code <context:component-scan>} element has an
 * {@code annotation-config} attribute; however, this annotation does not. This is because
 * in almost all cases when using {@code @ComponentScan}, default annotation config
 * processing (e.g. processing {@code @Autowired} and friends) is assumed. Furthermore,
 * when using {@link AnnotationConfigApplicationContext}, annotation config processors are
 * always registered, meaning that any attempt to disable them at the
 * {@code @ComponentScan} level would be ignored.
 *
 * <p>See {@link Configuration @Configuration}'s Javadoc for usage examples.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 3.1
 * @see Configuration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

	/**
	 * Alias for {@link #basePackages}.
	 * <p>Allows for more concise annotation declarations if no other attributes
	 * are needed &mdash; for example, {@code @ComponentScan("org.my.pkg")}
	 * instead of {@code @ComponentScan(basePackages = "org.my.pkg")}.
	 *
	 * basePackages的别名
	 * 允许在不需要其他属性的情况下进行更简洁的注释声明，例如:
	 * {@code @ComponentScan("org.my.pkg") 相当于 @ComponentScan(basePackages = "org.my.pkg")`}
	 */
	@AliasFor("basePackages")
	String[] value() default {};

	/**
	 * Base packages to scan for annotated components.
	 * <p>{@link #value} is an alias for (and mutually exclusive with) this
	 * attribute.
	 * <p>Use {@link #basePackageClasses} for a type-safe alternative to
	 * String-based package names.
	 *
	 * 扫描带注解的基础包，是value的别名，不能同时配置
	 * 使用basePackageClasses属性替代这种以字符串
	 */
	@AliasFor("value")
	String[] basePackages() default {};

	/**
	 * Type-safe alternative to {@link #basePackages} for specifying the packages
	 * to scan for annotated components. The package of each class specified will be scanned.
	 * <p>Consider creating a special no-op marker class or interface in each package
	 * that serves no purpose other than being referenced by this attribute.
	 *
	 * 扫描具体的类。basePackagesClasses属性的类型是Class数组，也就是说支持同时指定多个扫描类。
	 */
	Class<?>[] basePackageClasses() default {};

	/**
	 * The {@link BeanNameGenerator} class to be used for naming detected components
	 * within the Spring container.
	 * <p>The default value of the {@link BeanNameGenerator} interface itself indicates
	 * that the scanner used to process this {@code @ComponentScan} annotation should
	 * use its inherited bean name generator, e.g. the default
	 * {@link AnnotationBeanNameGenerator} or any custom instance supplied to the
	 * application context at bootstrap time.
	 * @see AnnotationConfigApplicationContext#setBeanNameGenerator(BeanNameGenerator)
	 *
	 * 配置beanName生成器，默认是BeanNameGenerator类。一般情况下，都是使用默认的beanName生成器，但是Spring实现了可配置beanName生成器。
	 */
	Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

	/**
	 * The {@link ScopeMetadataResolver} to be used for resolving the scope of detected components.
	 *
	 * 处理检测到的bean的scope范围。spring的bean是有作用域的，默认是singleton，这个默认值就是在ScopeMetaData类中指定的：{@code private String scopeName = "singleton";}
	 * 这个属性也是可选配置，默认的处理bean作用域的实现类是AnnotationScopeMetaDataResolver.class。如果设置就是取注解上获取指定的scope的value值，如果没有配置，就是用默认值singleton。
	 */
	Class<? extends ScopeMetadataResolver> scopeResolver() default AnnotationScopeMetadataResolver.class;

	/**
	 * Indicates whether proxies should be generated for detected components, which may be
	 * necessary when using scopes in a proxy-style fashion.
	 * <p>The default is defer to the default behavior of the component scanner used to
	 * execute the actual scan.
	 * <p>Note that setting this attribute overrides any value set for {@link #scopeResolver}.
	 * @see ClassPathBeanDefinitionScanner#setScopedProxyMode(ScopedProxyMode)
	 *
	 * 用于指定bean生成时的代理方式。默认是Default，则不使用代理。可选值有四个：DEFAULT，NO，INTERFACES，TARGET_CLASS。
	 */
	ScopedProxyMode scopedProxy() default ScopedProxyMode.DEFAULT;

	/**
	 * Controls the class files eligible for component detection.
	 * <p>Consider use of {@link #includeFilters} and {@link #excludeFilters}
	 * for a more flexible approach.
	 *
	 * 用于指定符合组件检测条件的类文件，默认值ClassPathScanningCandidateComponentProvider.DEFAULT_RESOURCE_PATTERN
	 */
	String resourcePattern() default ClassPathScanningCandidateComponentProvider.DEFAULT_RESOURCE_PATTERN;

	/**6
	 * Indicates whether automatic detection of classes annotated with {@code @Component}
	 * {@code @Repository}, {@code @Service}, or {@code @Controller} should be enabled.
	 *
	 * 是否对带有@Component @Repository @Service @Controller注解的类开启检测，默认是开启的。
	 */
	boolean useDefaultFilters() default true;

	/**
	 * Specifies which types are eligible for component scanning.
	 * <p>Further narrows the set of candidate components from everything in {@link #basePackages}
	 * to everything in the base packages that matches the given filter or filters.
	 * <p>Note that these filters will be applied in addition to the default filters, if specified.
	 * Any type under the specified base packages which matches a given filter will be included,
	 * even if it does not match the default filters (i.e. is not annotated with {@code @Component}).
	 * @see #resourcePattern()
	 * @see #useDefaultFilters()
	 *
	 * 自定义组件扫描的过滤规则，用于扫描组件。
	 */
	Filter[] includeFilters() default {};

	/**
	 * Specifies which types are not eligible for component scanning.
	 * @see #resourcePattern
	 *
	 * 自定义组件扫描的排除规则
	 */
	Filter[] excludeFilters() default {};

	/**
	 * Specify whether scanned beans should be registered for lazy initialization.
	 * <p>Default is {@code false}; switch this to {@code true} when desired.
	 * @since 4.1
	 *
	 * 组件扫描时是否采用懒加载，默认值为false（不开启）
	 */
	boolean lazyInit() default false;


	/**
	 * Declares the type filter to be used as an {@linkplain ComponentScan#includeFilters
	 * include filter} or {@linkplain ComponentScan#excludeFilters exclude filter}.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({})
	@interface Filter {

		/**
		 * The type of filter to use.
		 * <p>Default is {@link FilterType#ANNOTATION}.
		 * @see #classes
		 * @see #pattern
		 */
		FilterType type() default FilterType.ANNOTATION;

		/**
		 * Alias for {@link #classes}.
		 * @see #classes
		 */
		@AliasFor("classes")
		Class<?>[] value() default {};

		/**
		 * The class or classes to use as the filter.
		 * <p>The following table explains how the classes will be interpreted
		 * based on the configured value of the {@link #type} attribute.
		 * <table border="1">
		 * <tr><th>{@code FilterType}</th><th>Class Interpreted As</th></tr>
		 * <tr><td>{@link FilterType#ANNOTATION ANNOTATION}</td>
		 * <td>the annotation itself</td></tr>
		 * <tr><td>{@link FilterType#ASSIGNABLE_TYPE ASSIGNABLE_TYPE}</td>
		 * <td>the type that detected components should be assignable to</td></tr>
		 * <tr><td>{@link FilterType#CUSTOM CUSTOM}</td>
		 * <td>an implementation of {@link TypeFilter}</td></tr>
		 * </table>
		 * <p>When multiple classes are specified, <em>OR</em> logic is applied
		 * &mdash; for example, "include types annotated with {@code @Foo} OR {@code @Bar}".
		 * <p>Custom {@link TypeFilter TypeFilters} may optionally implement any of the
		 * following {@link org.springframework.beans.factory.Aware Aware} interfaces, and
		 * their respective methods will be called prior to {@link TypeFilter#match match}:
		 * <ul>
		 * <li>{@link org.springframework.context.EnvironmentAware EnvironmentAware}</li>
		 * <li>{@link org.springframework.beans.factory.BeanFactoryAware BeanFactoryAware}
		 * <li>{@link org.springframework.beans.factory.BeanClassLoaderAware BeanClassLoaderAware}
		 * <li>{@link org.springframework.context.ResourceLoaderAware ResourceLoaderAware}
		 * </ul>
		 * <p>Specifying zero classes is permitted but will have no effect on component
		 * scanning.
		 * @since 4.2
		 * @see #value
		 * @see #type
		 */
		@AliasFor("value")
		Class<?>[] classes() default {};

		/**
		 * The pattern (or patterns) to use for the filter, as an alternative
		 * to specifying a Class {@link #value}.
		 * <p>If {@link #type} is set to {@link FilterType#ASPECTJ ASPECTJ},
		 * this is an AspectJ type pattern expression. If {@link #type} is
		 * set to {@link FilterType#REGEX REGEX}, this is a regex pattern
		 * for the fully-qualified class names to match.
		 * @see #type
		 * @see #classes
		 */
		String[] pattern() default {};

	}

}
