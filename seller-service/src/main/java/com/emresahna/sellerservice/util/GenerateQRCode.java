package com.emresahna.sellerservice.util;

import com.emresahna.sellerservice.dto.SellerIdRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@UtilityClass
public class GenerateQRCode {

    public byte[] generateQRCode(String sellerId) {
        StringBuilder sellerDetails = new StringBuilder();
        sellerDetails.append("{").append("\"seller_id\":").append("\"").append(sellerId).append("\"").append("}");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(sellerDetails.toString(), BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return outputStream.toByteArray();
    }

}
