package com.example.projectc1023i1.service.mapper;

import com.example.projectc1023i1.Dto.get_data.notification_maptruck.NotificationMaptruck;
import com.example.projectc1023i1.Dto.get_data.notification_maptruck.OrderMaptruck;
import com.example.projectc1023i1.Dto.get_data.order_maptruck.OrderDetailMaptruck;
import com.example.projectc1023i1.model.Notifcation;
import com.example.projectc1023i1.model.Order;
import com.example.projectc1023i1.model.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:02:55+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251023-0518, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class INotificationMapperImpl implements INotificationMapper {

    @Override
    public NotificationMaptruck toNotificationMaptruck(Notifcation notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationMaptruck.NotificationMaptruckBuilder notificationMaptruck = NotificationMaptruck.builder();

        notificationMaptruck.coupon( notification.getCoupon() );
        notificationMaptruck.createAt( notification.getCreateAt() );
        notificationMaptruck.deal( notification.getDeal() );
        notificationMaptruck.message( notification.getMessage() );
        notificationMaptruck.notificationId( notification.getNotificationId() );
        notificationMaptruck.order( orderToOrderMaptruck( notification.getOrder() ) );
        notificationMaptruck.user( notification.getUser() );

        return notificationMaptruck.build();
    }

    @Override
    public OrderMaptruck toOrderMaptruck(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderMaptruck.OrderMaptruckBuilder orderMaptruck = OrderMaptruck.builder();

        orderMaptruck.address( order.getAddress() );
        orderMaptruck.note( order.getNote() );
        orderMaptruck.orderCode( order.getOrderCode() );
        orderMaptruck.orderDate( order.getOrderDate() );
        orderMaptruck.orderId( (int) order.getOrderId() );
        orderMaptruck.paymentType( order.getPaymentType() );
        orderMaptruck.status( order.getStatus() );
        orderMaptruck.total( order.getTotal() );

        return orderMaptruck.build();
    }

    protected OrderDetailMaptruck orderDetailsToOrderDetailMaptruck(OrderDetails orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        OrderDetailMaptruck.OrderDetailMaptruckBuilder orderDetailMaptruck = OrderDetailMaptruck.builder();

        orderDetailMaptruck.orderDetailsId( orderDetails.getOrderDetailsId() );
        orderDetailMaptruck.price( orderDetails.getPrice() );
        orderDetailMaptruck.productVariant( orderDetails.getProductVariant() );
        orderDetailMaptruck.quality( orderDetails.getQuality() );

        return orderDetailMaptruck.build();
    }

    protected List<OrderDetailMaptruck> orderDetailsListToOrderDetailMaptruckList(List<OrderDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailMaptruck> list1 = new ArrayList<OrderDetailMaptruck>( list.size() );
        for ( OrderDetails orderDetails : list ) {
            list1.add( orderDetailsToOrderDetailMaptruck( orderDetails ) );
        }

        return list1;
    }

    protected com.example.projectc1023i1.Dto.get_data.order_maptruck.OrderMaptruck orderToOrderMaptruck(Order order) {
        if ( order == null ) {
            return null;
        }

        com.example.projectc1023i1.Dto.get_data.order_maptruck.OrderMaptruck.OrderMaptruckBuilder orderMaptruck = com.example.projectc1023i1.Dto.get_data.order_maptruck.OrderMaptruck.builder();

        orderMaptruck.address( order.getAddress() );
        orderMaptruck.note( order.getNote() );
        orderMaptruck.orderCode( order.getOrderCode() );
        orderMaptruck.orderDate( order.getOrderDate() );
        orderMaptruck.orderDetailsList( orderDetailsListToOrderDetailMaptruckList( order.getOrderDetailsList() ) );
        orderMaptruck.orderId( order.getOrderId() );
        orderMaptruck.paymentType( order.getPaymentType() );
        orderMaptruck.status( order.getStatus() );
        orderMaptruck.total( order.getTotal() );
        orderMaptruck.users( order.getUsers() );

        return orderMaptruck.build();
    }
}
