# The Movie
A movie displaying app  

App Modules
* app: Presentation Layer

* domain: Business Logic Layer

* data: Data Access Layer (used only remote data source)

* ui: View Items Layer

* core: Common used classes

* buildSrc: Dependency Management module


## Requirement
You should create apikey.properties file under the project root folder. Then please put your API KEY in this file. For example;
```
API_KEY="YOUR_API_KEY"
```
## Screenshots
<p align="center">
<img src="/preview/homepage.jpeg" width="20%"/>
<img src="/preview/detailpage.jpeg" width="20%"/>
<img src="/preview/preview_video.gif" width="20%"/>
</p>


## Tech Stack

* Multi Module Architecture
* ViewModel
* Navigation
* Hilt
* Coroutines
* Retrofit
* Flow
* Paging
* Glide
* Timber
