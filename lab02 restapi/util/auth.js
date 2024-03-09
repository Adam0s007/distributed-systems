const { sign, verify } = require('jsonwebtoken');
const { compare } = require('bcryptjs');
const { NotAuthError } = require('./errors');

const KEY = 'supersecret';

function createJSONToken(email) {
  return sign({ email }, KEY, { expiresIn: '1h' });
}

function validateJSONToken(token) {
  return verify(token, KEY);
}

function isValidPassword(password, storedPassword) {
  return compare(password, storedPassword);
}

function checkAuthMiddleware(req, res, next) {
  const token = req.cookies.authToken;
  if (!token) {
    console.log('NOT AUTH. TOKEN MISSING.');
    return res.status(403).redirect('/login');
  }
  try {
    const validatedToken = validateJSONToken(token);
    req.user = validatedToken;
    next();
  } catch (error) {
    console.log('NOT AUTH. TOKEN INVALID.');
    return res.status(302).redirect('/login');
  }
}

exports.createJSONToken = createJSONToken;
exports.validateJSONToken = validateJSONToken;
exports.isValidPassword = isValidPassword;
exports.checkAuth = checkAuthMiddleware;
