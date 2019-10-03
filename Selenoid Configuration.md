### Selenoid Configuration

* https://aerokube.com/selenoid/latest/
* https://www.swtestacademy.com/selenoid-tutorial/

#### Selenoid UI 
* open via http://localhost:8080/#/

#### Using VNC (live video in selenoid-ui)
Please check the following:

* You have enableVNC = true capability in your tests (`options.setCapability(“enableVNC”, true)`)
* You are using browser images with vnc in their name, e.g. selenoid/vnc:firefox:58.0.

#### Custom Test Name
You can set session name by setting capability “name” (`options.setCapability(“name”, testName)`);

#### Recording Videos for later use
Note: To be able to use this feature, you should have video-recorder image. If you don’t, please use the command below to pull image:
```
$ docker pull selenoid/video-recorder
```
Selenoid has a video recorder, you can activate it by passing enableVideo capability as true

`options.setCapability(“enableVideo”,true)`

Videos are stored under ~/.aerokube/selenoid/video

video name, frame rate, video screen size and video frame rate are also adjustable.
```
videoName : string
videoScreenSize : string
videoFrameRate : int
```
#### Can Selenoid pull browser images automatically?

No, we did not implement this feature intentionally. We consider that all such cluster maintenance tasks can influence performance and stability when done automatically

#### Recommendation on Maximum Parallel Session
**Default maximum parallel session limit is 5** (edit from me: but it's still runs only one thread?), but as you know, we can override this value. At this point, the critical question comes to mind. How many tests can I execute in parallel?

Aerokube suggestion for the max parallel session is the number of cores * 1.5 – 2
