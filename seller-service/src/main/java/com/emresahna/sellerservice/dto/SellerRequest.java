package com.emresahna.sellerservice.dto;

import lombok.Data;

@Data
public class SellerRequest {
    private String seller_name;
    private String tax_id;
    private String phone;
    private String email;
    private String account_number;
    private String bank_details;
}
