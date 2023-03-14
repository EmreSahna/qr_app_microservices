package com.emresahna.sellerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellerResponse {
    private String seller_name;
    private String tax_id;
    private String phone;
    private String email;
    private String account_number;
    private String bank_details;
}
