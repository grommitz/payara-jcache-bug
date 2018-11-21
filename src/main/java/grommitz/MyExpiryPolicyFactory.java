package grommitz;

import javax.cache.configuration.Factory;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;
import java.util.concurrent.TimeUnit;

public class MyExpiryPolicyFactory implements Factory<ExpiryPolicy> {

	@Override
	public ExpiryPolicy create() {
		return CreatedExpiryPolicy
				.factoryOf(new Duration(TimeUnit.SECONDS, 10))
				.create();
	}

}
