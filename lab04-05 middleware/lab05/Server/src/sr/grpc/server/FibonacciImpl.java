package sr.grpc.server;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.FibonacciInput;
import sr.grpc.gen.FibonacciList;
import sr.grpc.gen.FibonacciNumber;
import sr.grpc.gen.FibonacciServiceGrpc.FibonacciServiceImplBase;

public class FibonacciImpl extends FibonacciServiceImplBase {

    @Override
    public void findFibonacciNumbers(FibonacciList request, StreamObserver<FibonacciNumber> responseObserver) {
        if (request.getNumbersCount() == 0) {
            System.out.println("No numbers");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("No numbers").asRuntimeException());
            return;
        }
        if (request.getNumbersList().stream().anyMatch(input -> input.getNumber() < 1)) {
            System.out.println("Invalid number");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("Invalid numbers").asRuntimeException());
            return;
        }
        System.out.println("findFibonacciNumbers called with" + request.getNumbersList());

        for (FibonacciInput input : request.getNumbersList()) {
            long number = input.getNumber();
            long index = isFibonacci(number);
            if (index != -1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    responseObserver.onError(io.grpc.Status.CANCELLED.withDescription("Request interrupted").asRuntimeException());
                    return;
                }
                FibonacciNumber fibNumber = FibonacciNumber.newBuilder()
                        .setFibonacciNum(number)
                        .setSequenceIndex(index)
                        .build();
                responseObserver.onNext(fibNumber);
            }
        }
        responseObserver.onCompleted();
    }

    private long isFibonacci(long num) {
        if (num < 0) return -1;
        long a = 0;
        long b = 1;
        long index = 1;
        while (b <= num) {
            if (b == num) {
                return index;
            }
            long temp = b;
            b = a + b;
            a = temp;
            index++;
        }
        return -1;
    }
}
