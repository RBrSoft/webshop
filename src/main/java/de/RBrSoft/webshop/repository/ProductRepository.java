package de.RBrSoft.webshop.repository;

import de.RBrSoft.webshop.model.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    List<ProductResponse> products = Arrays.asList(
            new ProductResponse(
                    "1",
                    "AMD Ryzen 9 5950x",
                    "Extreme Processor with great performance",
                    79900,
                    Arrays.asList(
                            "AMD",
                            "Processor")
            ),
            new ProductResponse(
                    "2",
                    "INTEL CORE i7 10750",
                    "Extreme Processor with great performance",
                    99978,
                    Arrays.asList(
                            "INTEL",
                            "Processor")
            ),
            new ProductResponse(
                    "3",
                    "INTEL CORE i3 10320U",
                    "Low Budget Processor",
                    12849,
                    Arrays.asList(
                            "INTEL",
                            "Processor",
                            "LowBudget")
            )
    );

    public List<ProductResponse> findAll(String tag) {

        if (tag == null)
            return products;
        else {
            String lowerCaseTagResponse = tag.toLowerCase();

            // Modernere Art zu programmieren
            return products.stream()
                            .filter(p -> lowerCaseTags(p).contains(lowerCaseTagResponse))
                            .collect(Collectors.toList());

/*
            // Alte Variante
            List<ProductResponse> filtered = new ArrayList<>();
            for (ProductResponse p : products) {
                if (lowerCaseTags(p).contains(tag))
                    filtered.add(p);
            }
            return filtered;
*/
        }
    }

    private List<String> lowerCaseTags(ProductResponse p) {
        List<String> tags = p.getTags();

        // Modernere Art zu programmieren
        return tags.stream()
                .map(tag -> tag.toLowerCase())
                .collect(Collectors.toList());
    }

}
