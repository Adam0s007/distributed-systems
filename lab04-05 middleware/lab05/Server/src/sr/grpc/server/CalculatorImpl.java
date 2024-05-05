package sr.grpc.server;

import io.grpc.Status;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.ArithmeticOpArguments;
import sr.grpc.gen.ArithmeticOpResult;
import sr.grpc.gen.CalculatorServiceGrpc.CalculatorServiceImplBase;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;


public class CalculatorImpl extends CalculatorServiceImplBase {

    @Override
    public void operation(ArithmeticOpArguments request,
                          StreamObserver<ArithmeticOpResult> responseObserver) {
        if (request.getArgsCount() == 0) {
            System.out.println("No arguments");
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("No arguments").asRuntimeException());
            return;
        }
        System.out.println("multipleArgumentsRequest args: " + request.getOpType().name() + ", " + request.getArgsList());

        double res = calculateResult(request, responseObserver);
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(res).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    private double calculateResult(ArithmeticOpArguments request,
                                   StreamObserver<ArithmeticOpResult> responseObserver) {
        return switch (request.getOpType()) {
            case SUM -> request.getArgsList().stream().mapToDouble(Double::doubleValue).sum();
            case AVG -> request.getArgsList().stream().mapToDouble(Double::doubleValue).average().orElse(0);
            case MULT -> request.getArgsList().stream().mapToDouble(Double::doubleValue).reduce(1, (a, b) -> a * b);
            case MOST_FREQUENT -> calculateMode(request.getArgsList());
            case RANGE -> calculateRange(request.getArgsList());
            case MIN -> request.getArgsList().stream().mapToDouble(Double::doubleValue).min().orElse(0);
            case MAX -> request.getArgsList().stream().mapToDouble(Double::doubleValue).max().orElse(0);
            case VARIANCE -> {
                double mean = request.getArgsList().stream().mapToDouble(Double::doubleValue).average().orElse(0);
                yield request.getArgsList().stream().mapToDouble(x -> Math.pow(x - mean, 2)).average().orElse(Double.NaN);
            }
            case STDDEV -> {
                double mean = request.getArgsList().stream().mapToDouble(Double::doubleValue).average().orElse(0);
                double variance = request.getArgsList().stream().mapToDouble(x -> Math.pow(x - mean, 2)).average().orElse(0);
                yield Math.sqrt(variance);
            }

            default -> {
                System.out.println("Unexpected value: " + request.getOpType());
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid argument: " + request.getOpType()).asRuntimeException());
                yield 0;
            }
        };
    }

    private double calculateMode(List<Double> values) {
        Map<Double, Long> frequencyMap = values.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxFrequency = Collections.max(frequencyMap.values());
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Double.NaN);
    }

    private double calculateRange(List<Double> values) {
        double max = Collections.max(values);
        double min = Collections.min(values);
        return max - min;
    }

}
