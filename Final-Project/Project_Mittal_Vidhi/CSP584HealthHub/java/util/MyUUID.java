package util;

import java.util.UUID;

public class MyUUID {
	public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        return orderId;
    }
}
