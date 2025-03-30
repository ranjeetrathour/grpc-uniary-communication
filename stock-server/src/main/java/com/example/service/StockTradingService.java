package com.example.service;

import com.example.StockRepository;
import com.example.entity.Stock;
import com.mytrade.StockRequest;
import com.mytrade.StockResponse;
import com.mytrade.StockTradingServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@GrpcService
@AllArgsConstructor
public class StockTradingService extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        try {
            var stock = stockRepository.findByStockName(request.getStockSymbol());
            if (stock == null) {
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription("Stock not found for symbol: " + request.getStockSymbol())
                        .asRuntimeException());
                return;
            }
            String formattedTimestamp = stock.getLocalDateTime().toString();
            StockResponse response = StockResponse.newBuilder()
                    .setStockSymbol(stock.getStockName())
                    .setStockPrice(stock.getStockPrice())
                    .setTimeStamp(formattedTimestamp)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error fetching stock price").asRuntimeException());
        }
    }

    @Override
    public void createStock(StockResponse request, StreamObserver<StockResponse> responseObserver) {
        try {
            Stock savedStock = stockRepository.save(Stock.builder()
                    .uuid(UUID.randomUUID().toString())
                    .stockPrice(request.getStockPrice())
                    .stockName(request.getStockSymbol())
                    .localDateTime(LocalDateTime.now())
                    .build());

            StockResponse response = StockResponse.newBuilder()
                    .setStockSymbol(savedStock.getStockName())
                    .setStockPrice(savedStock.getStockPrice())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("Stock created successfully: {}", savedStock.getStockName());
        } catch (Exception e) {
            log.error("Error creating stock", e);
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to create stock").asRuntimeException());
        }
    }
}
