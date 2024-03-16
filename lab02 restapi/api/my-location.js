require("dotenv").config();

const axios = require("axios");

async function fetchLocation(latitude, longitude) {
  let my_key = "demo";
  let response;
  try {
    if (!latitude || !longitude) {
      my_key = process.env.IPINFO_API_KEY || my_key;
      response = await axios.get(`https://ipinfo.io?token=${my_key}`);
      //console.log(response.data);
    } else {
      my_key = process.env.GEODATASOURCE_API_KEY || my_key;
      const url = `https://api.geodatasource.com/v2/city?key=${my_key}&lat=${latitude}&lng=${longitude}&format=json`;
      response = await axios.get(url);
      //console.log(response.data);
    }
    return response.data;
  } catch (error) {
    //console.error("Error fetching location:", error);
    throw error;
  }
}

exports.fetchLocation = fetchLocation;
