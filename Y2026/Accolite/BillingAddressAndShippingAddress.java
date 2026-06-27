package Y2026.Accolite;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingAddressAndShippingAddress {
    
    public static void main(String[] args) {
        
    }

    public static void findCities(List<BillingAddressDTO> shippingList, List<BillingAddressDTO> billingList){
        // Step 1: Group shipping addresses by billingAddressId
        Map<Long, List<ShippingAddressDTO>> shippingByBillingId =
            shippingList.stream()
                .collect(Collectors.groupingBy(ShippingAddressDTO::getBillingAddressId));

        // Step 2: Process billing addresses and count matching shipping addresses
        Map<String, Long> cityCounts =
            billingList.stream()
                .filter(billing -> {
                    List<ShippingAddressDTO> shippings =
                        shippingByBillingId.getOrDefault(billing.getBillingAddressId(), List.of());

                    long matches = shippings.stream()
                        .filter(s -> s.getPinCode().equals(billing.getPinCode()))
                        .count();

                    return matches >= 2;
                })
                .collect(Collectors.groupingBy(
                    BillingAddressDTO::getCityName,
                    Collectors.counting()
                ));

        // Step 3: Filter cities with 500+ customers
        Map<String, Long> result =
            cityCounts.entrySet().stream()
                .filter(e -> e.getValue() >= 500)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue
                ));
    }
}

class BillingAddressDTO {
    Long billingAddressId;
    String cityName;
    String pinCode;
    Long customerId;
}

class ShippingAddressDTO {
    Long billingAddressId;
    Long shippingAddressId;
    String pinCode;
}