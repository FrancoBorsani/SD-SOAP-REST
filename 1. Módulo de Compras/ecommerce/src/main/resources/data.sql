/************************************* CATEGORIAS ********************************/

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(1, 'Categoria 1'),
(2, 'Categoria 2'),
(3, 'Categoria 3'),
(4, 'Categoria 4'),
(5, 'Categoria 5'),
(6, 'Categoria 6');

/************************************* PRODUCTOS ********************************/

INSERT INTO `producto` (`id_producto`, `descripcioncorta`, `descipcionlarga`, `imagen`, `precio`, `visible`, `stock`, `categoria_id_categoria`) VALUES
(2, 'Polo', 'Remera Blanca Polo Label', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 1299, b'1', 50, 1),
(3, 'Puma', 'Remera Blanca Puma Iconic t7 Slim', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 2299, b'1', 0, 1),
(4, 'Nike', 'Zapatilla Negra Nike AIR MAX BELLA TR 2', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 10499, b'1', 45, 2),
(5, 'Converse', 'Buzo Negro Converse All Star Cropped', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 3699, b'1', 30, 3),
(6, 'Chelsea Market', 'Pantalon Gris Chelsea Market Strong', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 2500, b'1', 4, 4),
(7, 'Clon', 'Babucha Azul Clon', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4801, b'1', 256, 4),
(8, 'Brooksfield', 'Pantalon Azul Brooksfield Kunz', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 6487, b'1', 520, 5),
(9, 'Fila', 'Zapatilla Rosa Fila Euro Jogger Femedge', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4990, b'1', 24, 6),
(10, 'Vizzano', 'Zapatilla Blanca Vizzano', 'https://images-na.ssl-images-amazon.com/images/I/71-3HjGNDUL.jpg', 4399, b'1', 75, 6);
