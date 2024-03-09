require("dotenv").config();
const express = require("express");
const axios = require("axios");
const { checkAuth } = require("./util/auth");

const authRoutes = require("./routes/auth");
const weatherRoutes = require("./routes/weather");
const airQualityRoutes = require("./routes/air-quality");
const app = express();
const port = 3000;

app.use(express.static("public"));
app.set("view engine", "ejs");
app.set("views", "./views");
app.use(express.urlencoded({ extended: true }));

app.use(authRoutes);
app.use(weatherRoutes);
app.use(airQualityRoutes);

app.get("/",checkAuth, async (req, res) => {
  res.status(200).render("search");
});

app.get("/not-found", (req, res) => {
  const message = req.query.message || "Page not found";
  res.render("error", { message });
});


app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
