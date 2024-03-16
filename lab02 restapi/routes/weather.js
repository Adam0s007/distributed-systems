require("dotenv").config();
const express = require("express");
const { checkAuth } = require("../util/auth");
const { validateWeatherRequest } = require("../util/validation");
const {
  fetchWeatherDataByCity,
  fetchWeatherDataByCoordinates,
} = require("../api/fetchWeatherData");
const { getStats } = require("../util/statistics");
const { fetchLocation } = require("../api/my-location");
const router = express.Router();

router.post("/weather", checkAuth, async (req, res) => {
  let { city, startDate, endDate } = req.body;
  let latitude = null;
  let longitude = null;
  if (!req.body.location) {
    const errMess = validateWeatherRequest(req);
    if (errMess !== "") {
      return res.redirect(`/not-found?message=${encodeURIComponent(errMess)}`);
    }
  } else {
    if (req.body.location !== "true") {
      // we have location data as: `${position.coords.latitude},${position.coords.longitude}`
      const locationData = req.body.location.split(",");
      latitude = locationData[0];
      longitude = locationData[1];
      //console.log(latitude, longitude);
    }
    try {
      const locationData = await fetchLocation(latitude, longitude);
      city = locationData.city;
      startDate = new Date().toISOString();
      endDate = new Date(
        new Date().setDate(new Date().getDate() + 5)
      ).toISOString();
    } catch (error) {
      return res.redirect(
        `/not-found?message=${
          error?.response?.data?.error?.title ||
          error?.response?.data?.error?.error_message ||
          error.message ||
          error
        }`
      );
    }
  }
  console.log(city, startDate, endDate);
  try {
    let weatherData;
    if (latitude && longitude) {
      weatherData = await fetchWeatherDataByCoordinates(
        latitude,
        longitude,
        startDate,
        endDate
      );
    } else {
      weatherData = await fetchWeatherDataByCity(city, startDate, endDate);
    }
    const statistics = getStats(weatherData);

    res.render("weather", {
      city: weatherData.resolvedAddress,
      cityOrigin: city,
      startDate,
      endDate,
      days: weatherData.days,
      currentConditions: weatherData.currentConditions,
      statistics,
    });
  } catch (error) {
    res.redirect(
      `/not-found?message=${error?.response?.data || error.response || error}`
    );
  }
});

module.exports = router;
