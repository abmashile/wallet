package za.co.wallet.service;

import org.springframework.stereotype.Service;
import za.co.wallet.entities.User;

import java.math.BigDecimal;

@Service
public class WalletTransactionValidator {
    public void validate(User investor, BigDecimal amount){
/*
        if(Objects.nonNull(investor)) {
            Long investorId = investor.getId();

            List<Account> products = investor.getProducts();
            Optional<Account> optionalProduct = products.stream().filter(p -> p.getProductType().equals(productType)).findAny();
            if (optionalProduct.isEmpty()) {
                throw new ProductWithdrawalException(String.format("Product of type %s not found for investor with id %d", productType.name(), investor.getId()));
            }
            Account product = optionalProduct.get();
            if (product.getProductType().equals(ProductType.RETIREMENT)) {
                if (ApplicationDateUtil.getAge(investor.getDateOfBirth()) <= 65) {
                    throw new ProductWithdrawalException(String.format("Investor with id = %d must be greater than 65 years old to withdraw for %s product type", investor.getId(), ProductType.RETIREMENT.name()));
                }
            }

            if (product.getBalance().compareTo(amount) < 1) {
                throw new ProductWithdrawalException(String.format("Cannot withdraw amount greater than current balance", investorId));
            }

            if (amount.divide(product.getBalance(), 2, RoundingMode.UP).compareTo(new BigDecimal(0.9)) > 0) {
                throw new ProductWithdrawalException(String.format("Cannot withdraw amount greater than 90%% of current balance"));
            }
        }
  */
    }
}
