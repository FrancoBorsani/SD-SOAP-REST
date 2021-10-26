import React, { useEffect, useState } from "react";
import IndexHeader from "components/Headers/IndexHeader.js";
import { Container, Spinner } from "reactstrap";
import ProductItem from "components/ProductItem";

function HomePage() {

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('https://fakestoreapi.com/products')
    .then(response => response.json())
    .then(data => {
        setProducts(data);
        setLoading(false)
      }
    );
  })

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
      <div className="row mt-3">
        {
            products.map(product =>
                <div className="col-md-4 pb-2" key={product.id}>
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
