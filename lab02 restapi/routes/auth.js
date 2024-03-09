const express = require("express");
const { add, get } = require("../data/user");
const { createJSONToken, isValidPassword } = require("../util/auth");
const { isValidEmail, isValidText } = require("../util/validation");
const {NotFoundError} = require("../util/errors");
const cookieParser = require("cookie-parser");
const bcrypt = require("bcryptjs");

const router = express.Router();

router.use(cookieParser());

router.get("/login", (req, res) => {
  const { credentialsError, loginError } = req.query;
  res.render("login", {
    credentialsError: credentialsError || "",
    loginError: loginError || "",
  });
});

router.get("/signup", (req, res) => {
  const { emailError, passwordError } = req.query;
  res.render("signup", {
    emailError: emailError || "",
    passwordError: passwordError || "",
  });
});


router.post("/signup", async (req, res) => {
  const { email, password } = req.body;

  if (!isValidEmail(email) || !isValidText(password, 6)) {
    let query = '?';
    if (!isValidEmail(email)) query += 'emailError=Invalid email.';
    if (!isValidText(password, 6)) query += '&passwordError=Password must be at least 6 characters long.';
    return res.redirect(`/signup${query}`);
  }

  try {
    let user = await get(email);
    if (user) {
      return res.redirect("/signup?emailError=Email already exists.");
    }
  }catch(err){}
  try{
    user = await add({ email, password });
    const token = createJSONToken(user.email);
    res.cookie("authToken", token, { httpOnly: true }).redirect("/");

  } catch (error) {
    console.error(error);
    return res.redirect("/signup?signupError=Unexpected error occurred.");
  }
});

router.post("/login", async (req, res) => {
  const { email, password } = req.body;

  try {
    const user = await get(email);
    let isValid = await isValidPassword(password, user.password);
    if (!isValid) {
      return res.redirect("/login?credentialsError=Invalid password.");
    }

    const token = createJSONToken(user.email);
    res.cookie("authToken", token, { httpOnly: true }).redirect("/");
  } catch (error) {
    console.error(error);
    if (error instanceof NotFoundError) {
      return res.status(403).redirect("/login?credentialsError=User not found with that email.");
    } else {
      return res.status(500).redirect("/login?loginError=Internal server error.");
    }
  }
});

router.get("/logout", (req, res) => {
  res.clearCookie("authToken").redirect("/login");
});

module.exports = router;
