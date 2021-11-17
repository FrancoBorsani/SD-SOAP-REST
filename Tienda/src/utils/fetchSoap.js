import axios from 'axios';

const url = 'http://localhost:8089/get_products?';

export const  postSaler = function(post){
 let resp;
    
        axios.post('http://localhost:8089/test?',post).then(response => {
            resp = response.data 
        }).catch(e => {
            console.log(e);
        });
        
     return resp
}


 const GetProducts = function(){

    const axios = require('axios');
let res;
axios.get('http://localhost:8089/get_products?').then(resp => {
    res=resp.data
    console.log(resp.data);
});
console.log(JSON.stringify(res))
return res
}

export default GetProducts;

