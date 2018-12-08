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

        shippingRepository.save(shippingBill);
        return shippingBill;
    }


}
