    package com.example.anysale.product.controller;

    import com.example.anysale.product.dto.ProductDTO;
    import com.example.anysale.product.service.ProductService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/products")
    public class ProductController {

        private final ProductService productService;

        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        // 상품 전체 조회
        @GetMapping
        public ResponseEntity<List<ProductDTO>> getAllProducts() {
            List<ProductDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        }

        // 상품 ID로 조회
        @GetMapping("/{itemCode}")
        public ResponseEntity<ProductDTO> getProductById(@PathVariable String itemCode) {
            Optional<ProductDTO> product = productService.getProductById(itemCode);
            return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        // 상품 등록
        @PostMapping
        public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
            ProductDTO savedProduct = productService.saveProduct(productDTO);
            return ResponseEntity.ok(savedProduct);
        }

        // 상품 수정
        @PutMapping("/{itemCode}")
        public ResponseEntity<ProductDTO> updateProduct(@PathVariable String itemCode, @RequestBody ProductDTO productDTO) {
            ProductDTO updatedProduct = productService.updateProduct(itemCode, productDTO);
            return ResponseEntity.ok(updatedProduct);
        }

        // 상품 삭제
        @DeleteMapping("/{itemCode}")
        public ResponseEntity<Void> deleteProduct(@PathVariable String itemCode) {
            productService.deleteProduct(itemCode);
            return ResponseEntity.noContent().build();
        }
    }
