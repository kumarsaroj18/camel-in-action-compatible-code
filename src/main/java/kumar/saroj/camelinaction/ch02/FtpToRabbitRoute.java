package kumar.saroj.camelinaction.ch02;

import org.apache.camel.builder.RouteBuilder;

public class FtpToRabbitRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("ftp://localhost:21/inbox"
                + "?username=test"
                + "&password=test123"
                + "&binary=true"
                + "&delete=false"
                + "&passiveMode=true"
                + "&delay=5000")  // poll every 5 seconds
                .log("Picked up file: ${header.CamelFileName}")
                .convertBodyTo(byte[].class) // ensure body is the file content
                .to("rabbitmq:my-exchange"
                        + "?hostname=localhost"
                        + "&portNumber=5672"
                        + "&username=guest"
                        + "&password=guest"
                        + "&routingKey=my.queue"
                        + "&autoDelete=false");   // durable exchange/queue

    }
}
