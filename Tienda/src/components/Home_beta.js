import React, { useEffect, useState } from "react";
import { Container, Spinner } from "reactstrap";
import ProductItem from "components/ProductItem";
import Filter from "components/Filter";
import axios from 'axios';
import  GetProducts from 'utils/fetchSoap';


const Home_beta = () => {

    // const res= [];



    //     const axios = require('axios');
    // axios.get('http://localhost:8089/get_products?').then(resp => {
    //     res=JSON.stringify(resp.data);
    //     console.log("lalalal");
    // });
    
    const axios = require('axios');

const res =  axios.get('http://localhost:8089/get_products?');

console.log(res) 
     const product = res;
    
          <div>
           {product.map(function(product, idx){
              return (<li key={idx}>{product.idProducto}</li>)
            })}
           </div>
      
   
        }
export default Home_beta;
