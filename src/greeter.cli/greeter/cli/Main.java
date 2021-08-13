
package greeter.cli;

import greeter.hello.service.HelloMessageService;

public class Main {

	public static void main(String... args) {

		HelloMessageService hms = new HelloMessageService();

		String msg = hms.getMessage();

		System.out.println(msg + " from a module!");
	}
}