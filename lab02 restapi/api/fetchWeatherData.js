require("dotenv").config();
const axios = require("axios");


async function fetchWeatherDataByCity(city, startDate, endDate) {
    const apiKey = process.env.VISUAL_CROSSING_WEB_SERVICE_API_KEY || "your-api-key";
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}/${startDate}/${endDate}?unitGroup=metric&elements=datetime%2CdatetimeEpoch%2Cname%2Caddress%2Ctempmax%2Ctempmin%2Ctemp%2Chumidity%2Cprecipprob%2Cwindspeedmean%2Ccloudcover%2Cuvindex%2Csunrise%2Csunset%2Cconditions&include=stats%2Cstatsfcst%2Cfcst%2Cremote%2Cobs%2Cdays&key=${apiKey}&contentType=json`;
     
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
    const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${latitude},${longitude}/${startDate}/${endDate}?unitGroup=metric&elements=datetime%2CdatetimeEpoch%2Cname%2Caddress%2Ctempmax%2Ctempmin%2Ctemp%2Chumidity%2Cprecipprob%2Cwindspeedmean%2Ccloudcover%2Cuvindex%2Csunrise%2Csunset%2Cconditions&include=stats%2Cstatsfcst%2Cfcst%2Cremote%2Cobs%2Cdays&key=${apiKey}&contentType=json`;
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
