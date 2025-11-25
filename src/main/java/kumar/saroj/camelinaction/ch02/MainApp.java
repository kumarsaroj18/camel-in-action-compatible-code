package kumar.saroj.camelinaction.ch02;

import org.apache.camel.main.Main;

public class MainApp {
    public static void main(String[] args) throws Exception {
        Main main = new Main();

        try {
            main.configure().addRoutesBuilder(new FtpToRabbitRoute());
            main.configure().addRoutesBuilder(new RabbitConsumerRoute());
            main.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
