
function getStats(weatherData) {
    let maxTemp = Number.MIN_VALUE;
    let minTemp = Number.MAX_VALUE;
    let averageTemp = weatherData.days.reduce((acc, day) => acc + day.temp, 0)/weatherData.days.length;
    let averageUVIndex = weatherData.days.reduce((acc, day) => acc + day.uvindex, 0)/weatherData.days.length;
    let averageHumidity = weatherData.days.reduce((acc, day) => acc + day.humidity, 0)/weatherData.days.length;
    let averagePrecipProb = weatherData.days.reduce((acc, day) => acc + day.precipprob, 0)/weatherData.days.length;
    let averageCloudCover = weatherData.days.reduce((acc, day) => acc + day.cloudcover, 0)/weatherData.days.length;
    let averageWindSpeed = weatherData.days.reduce((acc, day) => acc + day.windspeedmean, 0)/weatherData.days.length;
    maxTemp = Math.max(...weatherData.days.map(day => day.tempmax), maxTemp);
    minTemp = Math.min(...weatherData.days.map(day => day.tempmin), minTemp);

    return {
        averageTemp,
        maxTemp,
        minTemp,
        averageUVIndex,
        averageHumidity,
        averagePrecipProb,
        averageCloudCover,
        averageWindSpeed
    };
}


exports.getStats = getStats;