# A Simple weather advisor API group project

## Working brief

**Weather API** – get weather information for an inputted location. 
Create logic to determine how the user should prepare for a day outside (i.e. clothes/sunscreen/umbrella/coat etc).

### Result of brainstorm
* Our aspiration is to create a weather adviser as per the brief
* We will work to MVPs to demo and validate as we go
  * Sprint 1 
    * will be just to expose the weather data we are extracting
    * we will use a fixed location (probably London)
* We will use the MVC design pattern
  * Controller
    * weather (initially for testing)
    * recommend (intended endpoint)
  * Service
    * main "business" logic
  * Repository
    * ExternalWeatherAPIService (Interface)
      * getWeather() - possibly only function we need
* There will be ain internal data model for the Weather
  * Initially simple: temp, rain and cloud
  * Can be extended if business logic needs more
* The back end (repository) will be an external weather API
  * Initial research led to AccuWeather, but limited (free) access
  * Marcus found [OpenWeathermap](https://api.openweathermap.org) with less limits
* We will use 10am stand-ups
* We will use [Trello Scrum Board](https://trello.com/b/UTh9AoCc/the-weather-app)
* We used a [Google Jamboard](https://jamboard.google.com/d/1JNdv_1CggF4AdezxcHiEkBWsLXGNudiaKh0HUHy-2mY/viewer) to brainstorm
* Expecting to use 2-3 day sprints as project has limited time span
* We will use pair programming for experience and mutual support
* We will use Spiking for research
* We will use Mobs for initial project set up and code reviews

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
* UML for Sprint 1:
![UML for Sprint 1](doc/UML%20-%20Sprint%201.png)

## Sprint 2
* Added Swagger support for Weather and endpoints
  * [User friendly GUI](http://localhost:8080/swagger-ui/index.html#/weather-controller/getWeather_1)
  * [Computer friendly Restful](http://localhost:8080/v3/api-docs/weather-advisor-api)
* Example of Open API GUI:
  ![Example of Open API GUI](doc/Example%20API%20GUI.png)
