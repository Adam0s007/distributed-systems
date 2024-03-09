const express = require("express");
const { checkAuth } = require("../util/auth");
const {fetchAirQuality} = require("../api/air-quality");

const router = express.Router();


router.post("/air-quality", checkAuth, async (req, res) => {
    
    const {city, startDate,endDate} = req.body;
    console.log(city, startDate, endDate);
    try {
        const airData = await fetchAirQuality(city);
        res.render("air-quality", { 
            data:airData.data,
            city,
            startDate,
            endDate
        });
    } catch (error) {
        console.log("Error fetching air quality data:", error);
        res.status(404).redirect("/not-found?message=City not found");
    }
  });
  
module.exports = router;
