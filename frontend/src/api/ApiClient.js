import axios from 'axios';

export default class ApiClient {
  constructor(baseURL) {
    this.api = axios.create({
      baseURL: baseURL
    })
  }

  async fetchData(method, url, data) {
    try {
      const response = await this.api.request({
        method, url, data
      });
      return response;
    } catch(error) {
      console.error(error);
      throw error;
    }
  }
}