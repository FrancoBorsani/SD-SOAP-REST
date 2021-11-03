import React, { useEffect, useState } from "react";
import { Container, Spinner } from "reactstrap";
import ProductItem from "components/ProductItem";
import { getData } from "utils/fetchData";
import Filter from "components/Filter";

function HomePage() {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [order, setOrder] = useState("");
  const [keyword, setKeyword] = useState("");

  useEffect(() => {
    getData("productos").then(res => {
      setProducts(res)
      setLoading(false)
    })
  }, [])

  if (order === "Precio ascendente") {
      products.sort((a, b) =>  parseFloat(a.precio) - parseFloat(b.precio));
  } else if (order === "Precio descendente") {
      products.sort((a, b) => parseFloat(b.precio) - parseFloat(a.precio));
  }

  const handleChangeSearch = e => {

    setKeyword(e.target.value);
    
    let query = keyword ? `productos/search?keyword=${keyword}` : "productos"
    
    getData(query).then(res => {
        setProducts(res);
    }) 

  }

  if (loading) return (
    <div className="page-header clear-filter">
      <Container>
        <Spinner color="info" />
      </Container>
    </div>
  )

  return (
    <div className="clear-filter">
      <Container className="text-black">
        <div className="row">
          <Filter setProducts={setProducts} products={products} order={order} setOrder={setOrder} handleChangeSearch={handleChangeSearch} keyword={keyword} />
          <div className="col-md-9">
            <div className="row justify-content-center">
              {
                products.length === 0 && (
                  <div className="card">
                    <div className="card-body">
                      No hay productos que coincidan con la busqueda.
                    </div>
                  </div>
                )
              }
              {
                products.map(product =>
                  <div className="col-md-4" key={product.idProducto}>
                    <ProductItem product={product} />
                  </div>
                )
              }
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
}

export default HomePage;
