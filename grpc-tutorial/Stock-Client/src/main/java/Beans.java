import org.springframework.context.annotation.Bean;
import service.StockClientService;

public class Beans {
    @Bean
    public StockClientService stockClientService() {
        return new StockClientService();
    }

}
