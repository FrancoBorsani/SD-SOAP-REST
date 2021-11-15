/************************************* CATEGORIAS ********************************/

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(1, 'Categoria 1'),
(2, 'Categoria 2'),
(3, 'Categoria 3'),
(4, 'Categoria 4'),
(5, 'Categoria 5'),
(6, 'Categoria 6');

/************************************* PRODUCTOS ********************************/

<<<<<<< HEAD
INSERT INTO `producto` (`id_producto`, `descripcioncorta`, `descipcionlarga`, `imagen`, `precio`, `visible`, `stock`, `categoria_id_categoria`) VALUES
(2, 'Polo', 'Remera Blanca Polo Label', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 1299, b'1', 50, 1),
(3, 'Puma', 'Remera Blanca Puma Iconic t7 Slim', 'https://http2.mlstatic.com/D_NQ_NP_814079-MLA32943158931_112019-O.jpg', 2299, b'1', 0, 1),
(4, 'Nike', 'Zapatilla Negra Nike AIR MAX BELLA TR 2', 'https://static.dafiti.com.ar/p/nike-2609-796004-1-product.jpg', 10499, b'1', 45, 2),
(5, 'Converse', 'Buzo Negro Converse All Star Cropped', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 3699, b'1', 30, 3),
(6, 'Chelsea Market', 'Pantalon Gris Chelsea Market Strong', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 2500, b'1', 4, 4),
(7, 'Clon', 'Babucha Azul Clon', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4801, b'1', 256, 4),
(8, 'Brooksfield', 'Pantalon Azul Brooksfield Kunz', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 6487, b'1', 520, 5),
(9, 'Fila', 'Zapatilla Rosa Fila Euro Jogger Femedge', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4990, b'1', 24, 6),
(10, 'Vizzano', 'Zapatilla Blanca Vizzano', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4399, b'1', 75, 6);
=======
INSERT INTO `producto` (`id_producto`, `cantidad_vendida`, `descripcion`, `forma_de_pago`, `imagen`, `nombre`, `precio`, `stock`, `categoria_id_categoria`, `vendedor_id`) VALUES
(2, 0, 'Polo', 'debito', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 'Remera Blanca Polo Label', 1299, 50, 1, 1),
(3, 0, 'Puma', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 'Remera Blanca Puma Iconic t7 Slim', 2299, 0, 1, 1),
(4, 0, 'Nike', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg','Zapatilla Negra Nike AIR MAX BELLA TR 2', 10499, 45, 2, 1),
(5, 0, 'Converse', 'debito', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg','Buzo Negro Converse All Star Cropped',  3699,  30, 3, 1),
(6, 0, 'Chelsea Market', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg','Pantalon Gris Chelsea Market Strong', 2500,  4, 4, 1),
(7, 0, 'Clon', 'debito', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg','Babucha Azul Clon',  4801, 256, 4, 1),
(8, 0, 'Brooksfield', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg','Pantalon Azul Brooksfield Kunz', 6487, 520, 5, 1),
(9, 0, 'Fila', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 'Zapatilla Rosa Fila Euro Jogger Femedge', 4990, 24, 6, 1),
(10, 0, 'Vizzano', 'debito',  'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 'Zapatilla Blanca Vizzano', 4399, 75, 6, 1);
>>>>>>> 7bbcd498f44cabb1110d8a18b96101d8d56171ec
