
package greeter.hello.service;

import java.util.Random;
import greeter.hello.util.CapitalizeUtil;

public class HelloMessageService {

	private final Random random = new Random(System.currentTimeMillis());

	private static final String[] messages = new String[] {
		"Viper is about to blow!",
		"Last lap!",
		"Ouch!",
		"Viper has taken the lead!",
	};

	public String getMessage() {
		int messageIndex = random.nextInt(messages.length);
		return CapitalizeUtil.toUpperCase(messages[messageIndex]);
	}
}