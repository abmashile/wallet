package za.co.wallet.service.rest.handler;

public class WalletTransactionException extends RuntimeException{
    public WalletTransactionException(String message){
        super(message);
    }
}
