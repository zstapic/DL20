package hr.foi.air.webservice.handlers;

public interface MyWebserviceHandler {
    void onDataArrived(
            Object result,
            boolean ok,
            long timeStamp);
}
