require("dotenv").config();
const express = require("express");
const { checkAuth } = require("../util/auth");
const { validateWeatherRequest } = require("../util/validation");
const { fetchWeatherData } = require("../api/fetchWeatherData");
const { getStats } = require("../util/statistics");
const {fetchLocation} = require("../api/my-location");
const router = express.Router();

router.post("/weather", checkAuth, async (req, res) => {
  let { city, startDate, endDate } = req.body;
    
    if (!req.body.location) {
        const errMess = validateWeatherRequest(req);
        if (errMess !== "") {
            return res.redirect(`/not-found?message=${encodeURIComponent(errMess)}`);
        }
    }
    
    if (req.body.location) {
        try {
            const locationData = await fetchLocation();
            city = locationData.city;
            startDate = new Date().toISOString();
            endDate = new Date(new Date().setDate(new Date().getDate() + 5)).toISOString(); // Add 5 days to current date
        } catch (error) {
            console.error('Error fetching location data:', error);
            return res.redirect(`/not-found?message=${encodeURIComponent("Error fetching location data")}`);
        }
    }
  console.log(city, startDate, endDate);
  try {
    const weatherData = await fetchWeatherData(city, startDate, endDate);
    const statistics = getStats(weatherData);
    res.render("weather", {
      city: weatherData.resolvedAddress,
      cityOrigin: city,
      startDate,
      endDate,
      days: weatherData.days,
      statistics,
    });
  } catch (error) {
    console.error("Error fetching weather data:", error);
    res.status(404).redirect("/not-found?message=City not found");
  }
});

module.exports = router;
