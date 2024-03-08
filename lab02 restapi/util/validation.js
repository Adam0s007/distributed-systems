function isValidText(value, minLength = 1) {
  return value && value.trim().length >= minLength;
}

function isValidDate(value) {
  const date = new Date(value);
  return value && date !== 'Invalid Date';
}

function isValidImageUrl(value) {
  return value && value.startsWith('http');
}

function isValidEmail(value) {
  return value && value.includes('@');
}

function validateWeatherRequest(req){
  const { city, startDate, endDate } = req.body;
  let mess = ''
  if(!city || !startDate || !endDate || new Date(startDate) > new Date(endDate)){
    if(!city){
      mess = 'City is required'
    }
    if(!startDate){
      mess = 'Start date is required'
    }
    if(!endDate){
      mess = 'End date is required'
    }
    if(new Date(startDate) > new Date(endDate)){
      mess = 'Start date must be before end date'
    }  
  }
  return mess;
}
exports.validateWeatherRequest = validateWeatherRequest;
exports.isValidText = isValidText;
exports.isValidDate = isValidDate;
exports.isValidImageUrl = isValidImageUrl;
exports.isValidEmail = isValidEmail;