const express = require("express");
const axios = require("axios");
const app = express();
const port = 3000;
const { checkAuth } = require("./util/auth");
const authRoutes = require('./routes/auth');

app.use(express.static("public"));
app.set("view engine", "ejs");
app.set("views", "./views");
app.use(express.urlencoded({ extended: true }));

app.use(authRoutes);

app.get("/", (req, res) => {
  res.render("index");
});

app.get('/not-found', (req, res) => {
  const message = req.query.message || 'Page not found';
  res.render('error', { message });
});

app.get("/login", (req, res) => {
  const { credentialsError, loginError } = req.query;
  res.render("login", {
    credentialsError: credentialsError || "",
    loginError: loginError || ""
  });
});


app.get("/signup", (req, res) => {
  const { emailError, passwordError } = req.query;
  res.render("signup", {
    emailError: emailError || "",
    passwordError: passwordError || ""
  });
});


app.post("/weather", checkAuth, async (req, res) => {
  console.log(req.body);
  const city = req.body.city;
  const apiKey = "EN36E78G48HKJDM54PEM59AZG";
  const url = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${encodeURIComponent(
    city
  )}?unitGroup=metric&key=${apiKey}&contentType=json`;

  try {
    const response = await axios.get(url);
    const weatherData = response.data;
    res.render("weather", { weather: weatherData });
  } catch (error) {
    console.error("Error fetching weather data:", error);
    res.redirect('/not-found?message=City not found');
  }
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
