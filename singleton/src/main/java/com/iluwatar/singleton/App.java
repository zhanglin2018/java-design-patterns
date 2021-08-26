/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.singleton;

/**
 * <p>
 * Singleton pattern ensures that the class can have only one existing instance
 * per Java classloader instance and provides global access to it.
 * </p>
 *
 * <p>
 * One of the risks of this pattern is that bugs resulting from setting a
 * singleton up in a distributed environment can be tricky to debug since it
 * will work fine if you debug with a single classloader. Additionally, these
 * problems can crop up a while after the implementation of a singleton, since
 * they may start synchronous and only become async with time, so it may not be
 * clear why you are seeing certain changes in behavior.
 * </p>
 *
 * <p>
 * There are many ways to implement the Singleton. The first one is the eagerly
 * initialized instance in {@link IvoryTower}. Eager initialization implies that
 * the implementation is thread safe. If you can afford to give up control of
 * the instantiation moment, then this implementation will suit you fine.
 * </p>
 *
 * <p>
 * The other option to implement eagerly initialized Singleton is enum-based
 * Singleton. The example is found in {@link EnumIvoryTower}. At first glance,
 * the code looks short and simple. However, you should be aware of the
 * downsides including committing to implementation strategy, extending the enum
 * class, serializability, and restrictions to coding. These are extensively
 * discussed in Stack Overflow:
 * http://programmers.stackexchange.com/questions/179386/what-are-the-downsides-of-implementing
 * -a-singleton-with-javas-enum
 * </p>
 *
 * <p>
 * {@link ThreadSafeLazyLoadedIvoryTower} is a Singleton implementation that is
 * initialized on demand. The downside is that it is very slow to access since
 * the whole access method is synchronized.
 * </p>
 *
 * <p>
 * Another Singleton implementation that is initialized on demand is found in
 * {@link ThreadSafeDoubleCheckLocking}. It is somewhat faster than
 * {@link ThreadSafeLazyLoadedIvoryTower} since it doesn't synchronize the whole
 * access method but only the method internals on specific conditions.
 * </p>
 *
 * <p>
 * Yet another way to implement thread-safe lazily initialized Singleton can be
 * found in {@link InitializingOnDemandHolderIdiom}. However, this
 * implementation requires at least Java 8 API level to work.
 * </p>
 */

public class App {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

	/**
	 * Program entry point.
	 *
	 * @param args command line args
	 */
	public static void main(String[] args) {

		System.out.println("hello world111");

		// eagerly initialized singleton
		IvoryTower ivoryTower1 = IvoryTower.getInstance();
		IvoryTower ivoryTower2 = IvoryTower.getInstance();
		log.info("ivoryTower1={}", ivoryTower1);
		log.info("ivoryTower2={}", ivoryTower2);

		// lazily initialized singleton
		ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower1 = ThreadSafeLazyLoadedIvoryTower.getInstance();
		ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower2 = ThreadSafeLazyLoadedIvoryTower.getInstance();
		log.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
		log.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

		// enum singleton
		EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
		EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
		log.info("enumIvoryTower1={}", enumIvoryTower1);
		log.info("enumIvoryTower2={}", enumIvoryTower2);

		// double checked locking
		ThreadSafeDoubleCheckLocking dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
		log.info(dcl1.toString());
		ThreadSafeDoubleCheckLocking dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
		log.info(dcl2.toString());

		// initialize on demand holder idiom
		InitializingOnDemandHolderIdiom demandHolderIdiom = InitializingOnDemandHolderIdiom.getInstance();
		log.info(demandHolderIdiom.toString());
		InitializingOnDemandHolderIdiom demandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
		log.info(demandHolderIdiom2.toString());
	}
}
