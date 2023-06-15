package com.baraq.ecomm.order.service;

import com.baraq.ecomm.order.dto.RequestDTO.OrderRequestDTO;
import com.baraq.ecomm.order.enums.PaymentMode;
import com.baraq.ecomm.order.mapper.OrderMapper;
import com.baraq.ecomm.order.persistence.model.Order;
import com.baraq.ecomm.order.persistence.model.PincodeServiceMapping;
import com.baraq.ecomm.order.persistence.repo.OrderRepository;
import com.baraq.ecomm.order.persistence.repo.PincodeAvailabilityRepository;
import com.baraq.ecomm.product.service.ProductService;
import com.baraq.ecomm.shared.dto.OrderDTO;
import com.baraq.ecomm.shared.dto.ProductDTO;
import com.baraq.ecomm.user.service.UserService;
import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceDefaultImpl implements OrderService {
    private final ProductService productService;
    private final UserService userService;
    private final PincodeAvailabilityRepository pincodeAvailabilityRepository;
    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public OrderDTO createOrder(OrderRequestDTO request) {
        Preconditions.checkArgument(request != null, "Invalid data");
        Preconditions.checkArgument(request.getUserDTO() != null, "Invalid user");
        Preconditions.checkArgument(request.getProduct() != null, "Invalid product");
        Preconditions.checkArgument(request.getProduct().getProductId() != null, "Invalid productId");
        Preconditions.checkArgument(request.getProduct().getQty() != null && request.getProduct().getQty() >=1, "Invalid quantity");
        ProductDTO productDTO = productService.getProductByProductId(request.getProduct().getProductId());
        PincodeServiceMapping pincodeServiceMapping = pincodeAvailabilityRepository.findBySourcePinCodeAndDestPinCode(productDTO.getPincode(),request.getDestinationAddress()
                .getPincode()).orElseThrow(()->new IllegalArgumentException("no service available for the addresses entered"));
       if(!(PaymentMode.CASH.equals(PaymentMode.getPaymentMode(request.getPaymentMode())) && pincodeServiceMapping.getIsCashAvailable()) &&
               !(PaymentMode.PREPAID.equals(PaymentMode.getPaymentMode(request.getPaymentMode())) && pincodeServiceMapping.getIsOnlineAvailable())) {
           throw new IllegalArgumentException("Order failed because pincode is unserviceable");
       }
        request.getProduct().setAmount(productDTO.getPrice()*request.getProduct().getQty());
        Order order = new Order();
        Boolean productAvailable = productAvailable = productService.checkProductAvailabiltiy(request.getProduct().getProductId(), request.getProduct().getQty());
        if(productAvailable)
           return OrderMapper.convertToDto(orderRepository.save(order.addOrder(request)));
        else
            throw new IllegalArgumentException("Order failed because stock is insufficient");
    }
}
