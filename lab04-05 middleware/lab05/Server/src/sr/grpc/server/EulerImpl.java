package sr.grpc.server;


import io.grpc.stub.StreamObserver;
import sr.grpc.gen.EulerInput;

import sr.grpc.gen.EulerList;
import sr.grpc.gen.EulerServiceGrpc;
import sr.grpc.gen.EulerStream;


public class EulerImpl extends EulerServiceGrpc.EulerServiceImplBase {

    @Override
    public void streamEulerNumber(EulerInput request, StreamObserver<EulerStream> responseObserver) {
        if (!request.hasField(EulerInput.getDescriptor().findFieldByName("terms"))) {
            System.out.println("No terms for streamEulerNumber!");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("No terms").asRuntimeException());
            return;
        }
        long terms = request.getTerms();
        if (terms <= 0) {
            System.out.println("Invalid arguments for streamEulerNumber");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("Terms must be positive").asRuntimeException());
            return;
        }
        System.out.println("streamEulerNumber args: " + request.getTerms() + " ");
        double factorial = 1.0;
        double e = 1.0;

        try {
            for (int i = 1; i <= terms; i++) {
                factorial *= i;
                double termValue = 1 / factorial;
                e += termValue;

                EulerStream result = EulerStream.newBuilder()
                        .setTermValue(termValue)
                        .setPartialSum(e)
                        .build();
                responseObserver.onNext(result);

                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Interrupted exception").asRuntimeException());
            return;
        }
        responseObserver.onCompleted();
    }

    @Override
    public void listEulerNumber(EulerInput request, StreamObserver<EulerList> responseObserver) {
        if(!request.hasField(EulerInput.getDescriptor().findFieldByName("terms"))) {
            System.out.println("No terms for listEulerNumber!");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("No terms").asRuntimeException());
            return;
        }
        if(request.getTerms() <= 0) {
            System.out.println("Invalid arguments for listEulerNumber");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("Terms must be positive").asRuntimeException());
            return;
        }
        System.out.println("listEulerNumber args: " + request.getTerms() + " ");
        long terms = request.getTerms();


        double factorial = 1.0;
        double e = 1.0;
        EulerList.Builder resultBuilder = EulerList.newBuilder();

        for (int i = 1; i <= terms; i++) {
            factorial *= i;
            double termValue = 1 / factorial;
            e += termValue;
            resultBuilder.addTerms(e);
        }

        EulerList result = resultBuilder.build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}

