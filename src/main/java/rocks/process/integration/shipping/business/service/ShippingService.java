package rocks.process.integration.shipping.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.process.integration.shipping.business.client.CustomerServiceClient;
import rocks.process.integration.shipping.domain.Amount;
import rocks.process.integration.shipping.domain.Customer;
import rocks.process.integration.shipping.domain.ShippingBill;
import rocks.process.integration.shipping.repository.ShippingRepository;

import java.util.List;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;
    @Autowired
    private CustomerServiceClient customerService;



    public ShippingBill processShipping(Long customerId, String orderId, String trackingId, String status) throws Exception {

    	Customer customer = customerService.retrieveCustomerById(customerId);
         ShippingBill shippingBill = new ShippingBill(customer, orderId, trackingId, status);
//        Discount discount = calculateDiscount(customerId, amount, numberOfItems);
//        transaction.setDiscount(discount.getFactor());
//        Amount discountedAmount = applyDiscount(amount, discount);
//        Amount remainingAmount = deductLoyaltyPoints(customer, discountedAmount);
//        transaction.setChargingAmount(remainingAmount.getValue());
//        if (remainingAmount.getValue() > 0) {
//            try {
//                transaction.setCardTransactionId(chargeCreditCard(customer, remainingAmount, transaction).getCardTransactionId());
//                transaction.setCanceled(false);
//            } catch (Exception e) {
//                restoreLoyaltyPoints(customer, new Amount(discountedAmount.getValue() - remainingAmount.getValue()));
//                transaction.setCanceled(true);
//                transactionRepository.save(transaction);
//                throw new Exception("Credit card transaction failed due to " + e.getMessage() + ".");
//            }
//        }
        shippingRepository.save(shippingBill);
        return shippingBill;
    }

//    public Discount calculateDiscount(Long customerId, Amount amount, Integer numberOfItems) {
//        Customer customer = customerService.retrieveCustomerById(customerId);
//        if (amount.getValue() >= 1000 && customer.getType().equals("Business")) {
//            if (numberOfItems >= 10) {
//                return new Discount(0.15);
//            } else {
//                return new Discount(0.10);
//            }
//        }
//        if (amount.getValue() >= 500 && customer.getType().equals("Private")) {
//            return new Discount(0.05);
//        }
//        return new Discount(0.00);
//    }
//
//    private Amount applyDiscount(Amount amount, Discount discount) {
//        return new Amount(amount.getValue() * (1 - discount.getFactor()));
//    }
//
//    private Amount deductLoyaltyPoints(Customer customer, Amount amount) {
//        Integer loyaltyMoney = customer.getLoyaltyPoints() / 1000;
//        if (loyaltyMoney >= amount.getValue()) {
//            customer.setLoyaltyPoints((int) ((loyaltyMoney - amount.getValue())*1000));
//            customerService.editLoyaltyBalance(customer);
//            return new Amount((double) 0);
//        }
//        customer.setLoyaltyPoints(0);
//        customerService.editLoyaltyBalance(customer);
//        return new Amount(amount.getValue() - loyaltyMoney);
//    }
//
//    private Transaction chargeCreditCard(Customer customer, Amount amount, Transaction transaction) {
//        transaction.setCardTransactionId(psp.chargeCreditCard(amount, customer.getCardNumber(), customer.getSecurityCode()).getTransactionId());
//        return transaction;
//    }
//
//    private void restoreLoyaltyPoints(Customer customer, Amount amount) {
//        customer.setLoyaltyPoints((int) (amount.getValue()*1000));
//        customerService.editLoyaltyBalance(customer);
//    }

    
    
    
    
//    public Transaction retrievePayment(Long transactionId) {
//        throw new UnsupportedOperationException();
//    }
//
//    public List<Transaction> listPayments(String customerId) {
//        throw new UnsupportedOperationException();
//    }
//
//    public Transaction updateCanceledPayment(Long transactionId, Long cardTransactionId) {
//        throw new UnsupportedOperationException();
//    }
//
//    public void deleteCanceledPayment(Long transactionId) {
//        throw new UnsupportedOperationException();
//    }
}
