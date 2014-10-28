package micro.test.akka;

import scala.Serializable;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 
 * @author lf E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年10月28日 上午10:37:25
 */
public class Tester {
	public static class Greeting implements Serializable {
		private static final long serialVersionUID = 1L;
		public final String who;

		public Greeting(String who) {
			this.who = who;
		}
	}

	public static class GreetingActor extends UntypedActor {
		LoggingAdapter log = Logging.getLogger(getContext().system(), this);

		public void onReceive(Object message) throws Exception {
			if (message instanceof Greeting)
				log.info("Hello " + ((Greeting) message).who);
		}
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("Actor-System");
		ActorRef greeter = system.actorOf(Props.create(GreetingActor.class), "greeter");
		greeter.tell(new Greeting("Charlie Parker"), ActorRef.noSender());
	}
}
