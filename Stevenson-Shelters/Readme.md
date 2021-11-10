# DESCRIPTION

Often when you go outside, it feels much warmer or colder than the weatherman predicted. 
This is not a conspiracy. It is simply that the predicted temperature is not always what the real world feels like. 
Most weather channels and meteorology apps have started using this term to give folks a better understanding of how they will actually feel out in their environment. 
They do this by collecting current weather readings from weather stations called Stevenson Screen shelters.

A Stevenson Screen (pictured above) is a standard shelter that protects meteorological instruments which measure things like temperature, 
dew point, wind speed, rain, etc. These readings are used to calculate the different temperatures that appear in your weather report.

Dew point is the temperature at below which water droplets (or dew) begin to form. 
The relationship between dew point (D), temperature in degrees Celsius (T), and relative humidity in percentage between 0 and 100 (R) 
is expressed in the following formula: 𝐷 = 𝑇−((100−𝑅)/5)

Heat index is a measure of how hot it feels when relative humidity is factored in with the actual temperature. 
It is calculated in a number of different ways but for our example, consider the formula for heat index (HI): 
𝐻𝐼 = 𝑐1 + 𝑐2*𝑇 + 𝑐3*𝑅 + 𝑐4*𝑇*𝑅 + 𝑐5*𝑇^2 + 𝑐6*𝑅^2 + 𝑐7*𝑇^2*𝑅 + 𝑐8*𝑇*𝑅^2 + 𝑐9*𝑇^2*𝑅^2
where T is the temperature in degrees Celsius, R is the relative humidity in percent, and the coefficients are: 
c1 = -8.78469475556, c2 = 1.61139411, c3 = 2.33854883889, c4 = -0.14611605, c5 = -0.012308094, c6 = -0.0164248277778, c7 = 0.002211732, 
c8 = 0.00072546, and c9 = -0.000003582.

Wind chill is related to heat index and is used when the real-feel temperature is lower than the actual temperature. 
There is some variation on how it is calculated depending on where you are but here in the United States, it is calculated with this formula:
𝑊𝐶 = 35.74 + 0.6215*𝑇 − 35.75*𝑣 + 0.16 + 0.4275*𝑇*𝑣 + 0.16
where WC is the wind chill based on the air temperature in degrees Fahrenheit (T) and the wind speed in miles per hour (v).
