require("dotenv").config();
const express = require("express");
const axios = require("axios");
const app = express();
const port = 3000;
const { checkAuth } = require("./util/auth");
const {validateWeatherRequest} = require("./util/validation")
const authRoutes = require("./routes/auth");


const {fetchWeatherData} = require("./api/fetchWeatherData");
const {getStats} = require("./util/statistics");


app.use(express.static("public"));
app.set("view engine", "ejs");
app.set("views", "./views");
app.use(express.urlencoded({ extended: true }));

app.use(authRoutes);


app.get("/",checkAuth, async (req, res) => {
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
  console.log(city, startDate, endDate);
  try {
      const weatherData = await fetchWeatherData(city, startDate, endDate);
      //console.log(weatherData);
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


app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
