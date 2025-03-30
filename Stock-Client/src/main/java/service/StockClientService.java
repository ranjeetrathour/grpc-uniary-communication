package service;

import com.mytrade.StockRequest;
import com.mytrade.StockResponse;
import com.mytrade.StockTradingServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

    @GrpcClient("stockClient")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub stockTradingServiceBlockingStub;

    public StockResponse getStockPrice(String id){
//        StockRequest.newBuilder().setStockSymbol(id).build();
        return stockTradingServiceBlockingStub.getStockPrice(StockRequest.newBuilder().setStockSymbol(id).build());
    }
}
