require("dotenv").config();

const axios = require('axios');

async function fetchLocation() {
    const my_key = process.env.IPINFO_API_KEY || "demo";
  try {
    const response = await axios.get(`https://ipinfo.io?token=${my_key}`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error('Error fetching location:', error);
    throw error;
  }
}

exports.fetchLocation = fetchLocation;
