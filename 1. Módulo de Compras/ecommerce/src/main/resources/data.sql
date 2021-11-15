/************************************* CATEGORIAS ********************************/

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(1, 'Categoria 1'),
(2, 'Categoria 2'),
(3, 'Categoria 3'),
(4, 'Categoria 4'),
(5, 'Categoria 5'),
(6, 'Categoria 6');

/************************************* PRODUCTOS ********************************/

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
