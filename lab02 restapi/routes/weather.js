require("dotenv").config();
const express = require("express");
const { checkAuth } = require("../util/auth");
const {validateWeatherRequest} = require("../util/validation")
const {fetchWeatherData} = require("../api/fetchWeatherData");
const {getStats} = require("../util/statistics");

const router = express.Router();


router.post("/weather", checkAuth, async (req, res) => {
    let errMess = validateWeatherRequest(req);
    
    if(errMess !== ''){
      return res.redirect(`/not-found?message=${encodeURIComponent(errMess)}`);
    }
    const { city, startDate, endDate } = req.body;
    console.log(city, startDate, endDate);
    try {
        const weatherData = await fetchWeatherData(city, startDate, endDate);
        const statistics = getStats(weatherData);
        res.render("weather", { 
            city: weatherData.resolvedAddress,
            cityOrigin:city,
            startDate,
            endDate,
            days: weatherData.days,
            statistics 
        });
    } catch (error) {
        console.error("Error fetching weather data:", error);
        res.redirect("/not-found?message=City not found");
    }
  });
  
module.exports = router;
