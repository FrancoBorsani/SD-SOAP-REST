import React, { useEffect, useState } from "react";
import { Container, Spinner } from "reactstrap";
import ProductItem from "components/ProductItem";
import { getData } from "utils/fetchData";
import Filter from "components/Filter";

function HomePage() {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getData("productos").then(res => {
      setProducts(res)
      setLoading(false)
    })
  }, [])

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
        <div className="row justify-content-center mt-3">
          <div className="col-md-12">
            <Filter />
          </div>
          {
            products.map(product =>
              <div className="col-md-4" key={product.idProducto}>
                <ProductItem product={product} />
              </div>
            )
          }
        </div>
      </Container>
    </div>
  );
}

export default HomePage;
