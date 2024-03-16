const express = require("express");
const { checkAuth } = require("../util/auth");
const {fetchAirQuality} = require("../api/air-quality");

const router = express.Router();


router.post("/air-quality", checkAuth, async (req, res) => {
    
    const {city, startDate,endDate} = req.body;
    console.log(city, startDate, endDate);
    try {
        const airData = await fetchAirQuality(city);
        //console.log(airData.data);
        res.render("air-quality", { 
            data:airData.data,
            city,
            startDate,
            endDate
        });
    } catch (error) {
        console.log("errorrrrrr:",error)
        res.status(404).redirect(`/not-found?${error?.response?.data || error?.message || error}`);
    }
  });
  
module.exports = router;
