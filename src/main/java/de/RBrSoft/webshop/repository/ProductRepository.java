package de.RBrSoft.webshop.repository;

import de.RBrSoft.webshop.model.ProductCreateRequest;
import de.RBrSoft.webshop.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductRepository {

    List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "AMD Ryzen 9 5950x",
                        "Extreme Processor with great performance",
                        79900,
                        Arrays.asList(
                                "AMD",
                                "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "INTEL CORE i7 10750",
                        "Extreme Processor with great performance",
                        99978,
                        Arrays.asList(
                                "INTEL",
                                "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "INTEL CORE i3 10320U",
                        "Low Budget Processor",
                        12849,
                        Arrays.asList(
                                "INTEL",
                                "Processor",
                                "LowBudget")
                ));
    }

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

    public Optional<ProductResponse> findById(String id) {
        Optional<ProductResponse> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return product;
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductResponse response) {
        products.add(response);
        return response;
    }

}
