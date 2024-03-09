require("dotenv").config();
async function fetchAirQuality(city) {
    console.log(city)
    const api_key = process.env.AIR_QUALITY_API_KEY || "demo";
    const apiUrl = `http://api.waqi.info/feed/${city}/?token=${api_key}`;
    try {
    const response = await fetch(apiUrl);
    const data = await response.json();
    if (data.status === "ok") {
        return data
    } else {
       throw new Error("Failed to load AQI data.");
    }
  } catch (error) {
    console.error("Error fetching weather data:", error);
    throw error; 
  }
}

exports.fetchAirQuality = fetchAirQuality;