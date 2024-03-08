require("dotenv").config();
const express = require("express");
const axios = require("axios");
const app = express();
const port = 3000;
const { checkAuth } = require("./util/auth");
const {validateWeatherRequest} = require("./util/validation")
const authRoutes = require("./routes/auth");
app.use(express.static("public"));
app.set("view engine", "ejs");
app.set("views", "./views");
app.use(express.urlencoded({ extended: true }));

app.use(authRoutes);

app.get("/", (req, res) => {
  res.render("index");
});

app.get("/not-found", (req, res) => {
  const message = req.query.message || "Page not found";
  res.render("error", { message });
});

app.get("/login", (req, res) => {
  const { credentialsError, loginError } = req.query;
  res.render("login", {
    credentialsError: credentialsError || "",
    loginError: loginError || "",
  });
});

app.get("/signup", (req, res) => {
  const { emailError, passwordError } = req.query;
  res.render("signup", {
    emailError: emailError || "",
    passwordError: passwordError || "",
  });
});

app.post("/weather", checkAuth, async (req, res) => {
  let errMess = validateWeatherRequest(req);
  if(errMess !== ''){
    return res.redirect(`/not-found?message=${encodeURIComponent(errMess)}`);
  }
  const { city, startDate, endDate } = req.body;

  const apiKey = process.env.WEATHER_API_KEY || "your-api-key";

  const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${encodeURIComponent(city)}/${startDate}/${endDate}?unitGroup=metric&include=days&key=${apiKey}&contentType=json`;

  try {
      const response = await axios.get(url);
      const weatherData = response.data;
      
      const temperatures = weatherData.days.map(day => day.temp);
      const averageTemp = temperatures.reduce((acc, curr) => acc + curr, 0) / temperatures.length;
      const maxTemp = Math.max(...weatherData.days.map(day => day.tempmax));
      const minTemp = Math.min(...weatherData.days.map(day => day.tempmin));
      const averageUVIndex = weatherData.days.reduce((acc, day) => acc + (day.uvindex || 0), 0) / weatherData.days.length; 
      const statistics = {
          averageTemp,
          maxTemp,
          minTemp,
          averageUVIndex, 
         
      };
      console.log("Weather data for days:", weatherData.days);
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


app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
