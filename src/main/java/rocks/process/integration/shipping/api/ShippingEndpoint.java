package rocks.process.integration.shipping.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import rocks.process.integration.shipping.business.service.ShippingService;
import rocks.process.integration.shipping.domain.Amount;
import rocks.process.integration.shipping.domain.OrderMessage;
import rocks.process.integration.shipping.domain.ShippingBill;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class ShippingEndpoint {
    @Autowired
    private ShippingService shippingService;


    @PostMapping(path = "/shipping", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OrderMessage> requestShipping(@RequestBody OrderMessage orderMessage) {
        ShippingBill shippingBill = null;
        String trackingId = UUID.randomUUID().toString();

        
        try {
            String status = "";
        	if(Integer.parseInt(orderMessage.getStockAmount()) < 0)
        	{
        		status = "in queue";
        	}
        	else{ 
        		status = "shipped";
        		orderMessage.setStatus("Shipment Placed");
                orderMessage.setTrackingId(trackingId);
    		};
        	shippingBill = shippingService.processShipping(Long.parseLong(orderMessage.getCustomerId()), orderMessage.getOrderId(), trackingId, status );
        
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

     
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{trackingId}")
                .buildAndExpand(shippingBill.getTrackingId()).toUri();

        return ResponseEntity.created(location).body(orderMessage);
    }

   
}
