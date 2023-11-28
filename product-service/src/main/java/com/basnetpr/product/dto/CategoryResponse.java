package com.basnetpr.product.dto;

import java.util.List;

public record CategoryResponse(Long categoryId, String name, String description, List<ProductResponse> products){
}
