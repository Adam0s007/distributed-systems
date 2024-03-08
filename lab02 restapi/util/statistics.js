
function getStats(weatherData){
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
    return statistics;
}

exports.getStats = getStats;