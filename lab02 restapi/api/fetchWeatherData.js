require("dotenv").config();
const axios = require("axios");


async function fetchWeatherDataByCity(city, startDate, endDate) {
    const apiKey = process.env.VISUAL_CROSSING_WEB_SERVICE_API_KEY || "your-api-key";
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}/${startDate}/${endDate}?unitGroup=metric&key=${apiKey}&contentType=json`;
     
    try {
      const response = await axios.get(url);
      let weatherData = response.data;
      return weatherData; 
    } catch (error) {
      console.error("Error fetching weather data:", error);
      throw error; 
    }
  }

async function fetchWeatherDataByCoordinates(latitude, longitude, startDate, endDate) {
    const apiKey = process.env.VISUAL_CROSSING_WEB_SERVICE_API_KEY || "your-api-key";
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${latitude},${longitude}/${startDate}/${endDate}?unitGroup=metric&key=${apiKey}&contentType=json`;
    try {
      const response = await axios.get(url);
      let weatherData = response.data;
      return weatherData;
    } catch (error) {
      console.error("Error fetching weather data:", error);
      throw error;
    }
  }

exports.fetchWeatherDataByCity = fetchWeatherDataByCity;
exports.fetchWeatherDataByCoordinates = fetchWeatherDataByCoordinates;
