import axios from 'axios';

export default () => {
    return axios.create({
        baseURL : 'http://localhost:8083/api/v1',
    });
}