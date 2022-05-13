# A Simple weather advisor API group project

## Notes

### Spike from Matt on [OpenWeathermap](https://api.openweathermap.org)

* Endpoint: https://api.openweathermap.org/data/2.5/weather
* Arguments:
  * appid - (required) need to register for this
  * lat - (required) float value for degrees north
  * lon - (required) float value for degrees east
  * units - (optional) default uses Kelvin, so we will use "metric" to get Centigrade
* Locations:
  * London: lat=51.5072&lon=-0.1276
  * Chichester: lat=50.8376&lon=-0.7749
  * York: lat=53.9614&lon=-1.0739
* Some examples:
  * [Weather in London](https://api.openweathermap.org/data/2.5/weather?lat=51.5072&lon=-0.1276&appid=7eff9edfb26b5501801fe33731f11aa4&units=metric)
  * [Weather in Chichester](https://api.openweathermap.org/data/2.5/weather?lat=50.8376&lon=-0.7749&appid=7eff9edfb26b5501801fe33731f11aa4&units=metric)
  * [Weather in York](https://api.openweathermap.org/data/2.5/weather?lat=53.9614&lon=-1.0739&appid=7eff9edfb26b5501801fe33731f11aa4&units=metric)

