package grommitz;


import fish.payara.cdi.jsr107.impl.NamedCache;

import javax.cache.Cache;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Stateless
@Path("/cache")
@Consumes("text/plain")
@Produces("text/plain")
public class Api {

	/*
	Accessing the api produces this in the log:

	Caused by: java.lang.ClassNotFoundException: grommitz.MyExpiryPolicyFactory
		at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
		at com.sun.enterprise.loader.CurrentBeforeParentClassLoader.loadClass(CurrentBeforeParentClassLoader.java:83)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
		at javax.cache.configuration.FactoryBuilder$ClassFactory.create(FactoryBuilder.java:140)
		at com.hazelcast.cache.impl.AbstractCacheRecordStore.<init>(AbstractCacheRecordStore.java:159)
		at com.hazelcast.cache.impl.CacheRecordStore.<init>(CacheRecordStore.java:61)
		at com.hazelcast.cache.impl.CacheService.createNewRecordStore(CacheService.java:69)
		at com.hazelcast.cache.impl.CachePartitionSegment.createNew(CachePartitionSegment.java:51)
		at com.hazelcast.cache.impl.CachePartitionSegment.createNew(CachePartitionSegment.java:37)
		at com.hazelcast.util.ConcurrencyUtil.getOrPutSynchronized(ConcurrencyUtil.java:73)
		at com.hazelcast.cache.impl.CachePartitionSegment.getOrCreateRecordStore(CachePartitionSegment.java:73)
		at com.hazelcast.cache.impl.AbstractCacheService.getOrCreateRecordStore(AbstractCacheService.java:316)
		at com.hazelcast.cache.impl.operation.AbstractCacheOperation.beforeRun(AbstractCacheOperation.java:70)
		at com.hazelcast.spi.impl.operationservice.impl.OperationRunnerImpl.run(OperationRunnerImpl.java:189)
		at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.process(OperationThread.java:120)
		at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.run(OperationThread.java:100)

	 */

	@Inject
	@NamedCache(cacheName = "mycache", expiryPolicyFactoryClass = MyExpiryPolicyFactory.class)
	Cache<String, Instant> cache;

	@POST
	public Response post(String s) {
		cache.put(s, Instant.now());
		return Response.ok().build();
	}

	@GET
	public List<String> cachedItems() {
		List<String> list = new ArrayList<>();
		Iterator<Cache.Entry<String, Instant>> it = cache.iterator();
		while(it.hasNext()) {
			list.add(it.next().getKey());
		}
		return list;
	}

}
