require("dotenv").config();
const axios = require("axios");
async function fetchWeatherData(city, startDate, endDate) {
    const apiKey = process.env.WEATHER_API_KEY || "your-api-key";
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${encodeURIComponent(city)}/${startDate}/${endDate}?unitGroup=metric&include=days&key=${apiKey}&contentType=json`;
  
    try {
      const response = await axios.get(url);
      const weatherData = response.data;
      return weatherData; 
    } catch (error) {
      console.error("Error fetching weather data:", error);
      throw error; 
    }
  }

  
exports.fetchWeatherData = fetchWeatherData;