const express = require('express');
const axios = require('axios');
const router = express.Router();
const { checkAuth } = require('../util/auth');
const { getStats } = require('../util/statistics');
const { fetchWeatherData } = require('../api/fetchWeatherData');
// Utility function to get total count of cities
async function getTotalCitiesCount() {
  const url = 'http://geodb-free-service.wirefreethought.com/v1/geo/cities?hateoasMode=off';
  try {
    const response = await axios.get(url);
    return response.data.metadata.totalCount;
  } catch (error) {
    console.error('Error fetching total cities count:', error);
    throw new Error('Failed to fetch total cities count.');
  }
}

// Utility function to get random city
async function getRandomCity(offset) {
  const url = `http://geodb-free-service.wirefreethought.com/v1/geo/cities?limit=1&offset=${offset}&hateoasMode=off`;
  try {
    const response = await axios.get(url);
    return response.data.data[0].name; // Assuming name is available
  } catch (error) {
    console.error('Error fetching random city:', error);
    throw new Error('Failed to fetch random city.');
  }
}

router.post("/randomCity", checkAuth, async (req, res) => {
    
    const { startDate, endDate } = req.body;
    if(!startDate || !endDate || new Date(startDate) > new Date(endDate)){
        return res.redirect(`/not-found?message=${encodeURIComponent('Invalid date range')}`);
    }
    try {
        const totalCitiesCount = await getTotalCitiesCount();
        const randomCity = await getRandomCity(Math.floor(Math.random() * totalCitiesCount));
        const weatherData = await fetchWeatherData(randomCity, startDate, endDate);
        const statistics = getStats(weatherData);
        res.render("weather", { 
            city: weatherData.resolvedAddress,
            days: weatherData.days,
            statistics 
        });
    } catch (error) {
        console.error("Error fetching weather data:", error);
        res.redirect("/not-found?message=City not found");
    }
  });

module.exports = router;
