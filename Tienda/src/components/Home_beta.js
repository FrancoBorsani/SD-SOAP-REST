import { Container, Spinner } from "reactstrap";
import axios from 'axios';
import React, { Component } from "react";


const url = 'http://localhost:8089/get_products?';
var myObject  = [];
const axiosTest = axios.get


axiosTest(url).then(function(axiosTestResult) {

   
    var myObject = JSON.parse(axiosTestResult.data);
    console.log(myObject)
        console.log("caca")
    return JSON.parse(axiosTestResult.data);
})
export class Example1 extends Component {

    constructor(props) {

        super(props);

        this.state = {

            stringData: myObject

        };

    }


    render() {

        // Parsed valued from string

        const valuesArray = JSON.parse(this.state.stringData);


        return (

            <>

                <div>

                    <h3>Using local JSON file Array</h3>

                    <ul>

                        {valuesArray.map(item => {

                            return <li>{item}</li>;

                        })}

                    </ul>

                </div>

            </>

        );

    }

}


export default Example1;
/*
    return (
        <div className="row">
            <Filter
                setProducts={setProducts}
                products={products}
                categories={categories}
                order={order}
                setOrder={setOrder}
                handleChangeSearch={handleChangeSearch}
                keyword={keyword}
                categorySelected={categorySelected}
                handleChangeCategory={handleChangeCategory}
                rangeOfPrice={rangeOfPrice}
                handleChangeRangeOfPrice={handleChangeRangeOfPrice}
            />
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
                    {   products.length > 0 &&
                        products.map(product =>
                            <div className="col-md-4" key={product.idProducto}>
                                <ProductItem product={product} />
                            </div>
                        )
                    }
                </div>
            </div>
        </div>
    )
}

export default Home;*/
