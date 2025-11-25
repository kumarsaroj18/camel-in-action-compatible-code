package kumar.saroj.camelinaction.ch02;

import org.apache.camel.builder.RouteBuilder;

public class RabbitConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("rabbitmq:my-exchange"
                + "?hostname=localhost"
                + "&portNumber=5672"
                + "&username=guest"
                + "&password=guest"
                + "&queue=my.queue"
                + "&routingKey=my.queue"
                + "&autoDelete=false"
                + "&declare=true")
                .log("Received from RabbitMQ: ${body}")
                .process(exchange -> {
                    String message = exchange.getMessage().getBody(String.class);
                    System.out.println(">>> Message consumed: " + message);
                });
    }
}