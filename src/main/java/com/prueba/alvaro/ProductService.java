package com.prueba.alvaro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductoRepository productoRepository;

    public HashMap<String, Object> getProductList(Producto producto){
        HashMap<String, Object> productos = productoRepository.getProductosList(producto);

        return productos;
    }
}
