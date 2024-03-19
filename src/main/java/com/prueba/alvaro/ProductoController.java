package com.prueba.alvaro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1")
public class ProductoController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/get/producto")
    @ResponseBody
    public HashMap<String, Object>getProductList(@RequestBody Producto producto) {
        HashMap<String, Object> productos = productService.getProductList(producto);

        return productos;
    }
}
