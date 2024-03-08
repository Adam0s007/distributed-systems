const axios = require('axios');

// Define the URL for the API request
const url = 'https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Chrzanów?unitGroup=metric&key=EN36E78G48HKJDM54PEM59AZG&contentType=json';

// Make the GET request using Axios
axios.get(url)
  .then(response => {
    // Handle success
    console.log('Weather data:', response.data);
  })
  .catch(error => {
    // Handle error
    console.error('Error fetching weather data:', error);
  });
